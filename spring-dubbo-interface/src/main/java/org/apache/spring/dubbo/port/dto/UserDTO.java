package org.apache.spring.dubbo.port.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @Serial
    private long serialVersionUID;

    private String username;

    private String email;

    private String password;

}
