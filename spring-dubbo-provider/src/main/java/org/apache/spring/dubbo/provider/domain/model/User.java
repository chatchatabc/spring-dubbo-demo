package org.apache.spring.dubbo.provider.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class User {
    private long id;
    private String username;
    private String password;

    private String email;

    private List<String> roles;
    private LocalDate dateCreated;
    private LocalDate lastLogin;

}
