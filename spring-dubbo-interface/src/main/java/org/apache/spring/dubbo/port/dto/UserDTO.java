package org.apache.spring.dubbo.port.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String email;

    private String password;

    private String matchingPassword;
}
