package org.apache.spring.dubbo.provider.login.domain;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class User {
    private long id;
    private String username;
    private String password;

    private String email;


}
