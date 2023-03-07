package org.apache.spring.dubbo.provider.user.domain.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;

@Data
public class User {
    private Long id;
    @Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters long")
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Username must have no special characters")
    private String username;
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Password must be SHA-1 hash")
    @Size(min = 3, max = 15, message = "Password must be between 6 to 15 characters long")
    private String password;
    @Email
    @Size(min = 10, max = 20, message = "Email must be between 10 to 20 characters long")
    private String email;
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "salt must have no special characters")
    @Size(min = 5, max = 10, message = "salt must be between 5 to 10 characters long")
    private String salt;

    @Expose
    private Timestamp dateat = Timestamp.from(Instant.now());

    @Expose
    private Timestamp lastlogin = Timestamp.from(Instant.now());


}
