package org.apache.spring.dubbo.provider.domain.model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.Instant;

@Data
public class User {

    @Nullable
    private Long id;
    private String username;
    private String password;
    private String email;
    private String salt;

    @Expose
    private Timestamp dateat = Timestamp.from(Instant.now());

    @Expose
    private Timestamp lastlogin = Timestamp.from(Instant.now());


}
