package org.apache.spring.dubbo.consumer.user.application.common.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 *
 * @author linux_china
 */
@Data
public class UserVO {
    @Pattern(regexp = "^[a-fA-F0-9]{40}$", message = "Username must have no special characters")
    private String username;
    @Email
    @Size(min = 10, max = 20, message = "Email must be between 10 to 20 characters long")
    private String email;
    @Size(min = 3, max = 15, message = "Password must be between 6 to 15 characters long")
    private String password;
    @Size(min = 3, max = 15, message = "Password must be between 6 to 15 characters long")
    private String matchingPassword;
}
