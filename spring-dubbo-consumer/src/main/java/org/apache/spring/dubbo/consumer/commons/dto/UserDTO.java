package org.apache.spring.dubbo.consumer.commons.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String email;

    private String password;

    private String matchingPassword;
}
