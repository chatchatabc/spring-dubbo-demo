package org.apache.spring.dubbo.provider.domain.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class User {

    @Nullable
    private Long id;
    private String username;
    private String password;

    private String email;

    @Expose
    private Timestamp dateat = Timestamp.from(Instant.now());

    @Expose
    private Timestamp lastlogin = Timestamp.from(Instant.now());


}
