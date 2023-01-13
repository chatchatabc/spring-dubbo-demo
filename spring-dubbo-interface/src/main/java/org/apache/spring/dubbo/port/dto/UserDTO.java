package org.apache.spring.dubbo.port.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private String email;

    private String password;

    private String matchingPassword;
}
