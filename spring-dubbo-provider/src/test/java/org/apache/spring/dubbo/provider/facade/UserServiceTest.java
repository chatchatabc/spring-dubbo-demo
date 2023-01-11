package org.apache.spring.dubbo.provider.facade;


import org.apache.spring.dubbo.inter.user.UserService;
import org.apache.spring.dubbo.provider.SpringBootBaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest extends SpringBootBaseTest {

    @Autowired
    UserService userService;



    @AfterEach
    void removeTestCreatedUser() throws IOException {
        userService.removeUser("sample@email.com");
    }

    @Test
    public void loginSuccessTest() throws IOException {

        Boolean val = userService.findByEmail("admin@email.com", "123");

        assertThat(val).isTrue();
    }

    @Test
    public void createUserSuccessTest() {

        Exception exception = assertThrows(IOException.class, () -> {
            userService.createUser("sample", "sample@email.com", "123456");
        });

        String expectedMessage = "1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }
}
