package org.apache.spring.dubbo.provider.Impl;

import org.apache.spring.dubbo.facade.dto.UserDTO;
import org.apache.spring.dubbo.provider.SpringBootBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeImplTest extends SpringBootBaseTest {

    @Autowired
    UserFacadeImpl userFacadeImpl;
    UserDTO userdto;

    @BeforeEach
    void setUp(){
        userdto = new UserDTO();
        userdto.setUsername("admin");
        userdto.setPassword("123");
        userdto.setEmail("admin@example.com");
    }


    @Test
    void authUser() throws IOException {
        UserDTO result = userFacadeImpl.authUser("admin@example.com", "123");
        System.out.println(result.getEmail());
        assertNotNull(result.getEmail());

    }

    @Test
    void registerUser() {

    }
}