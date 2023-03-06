package org.apache.spring.dubbo.provider.domain.service;


import org.apache.spring.dubbo.facade.UserFacade;
import org.apache.spring.dubbo.facade.dto.UserDTO;
import org.apache.spring.dubbo.provider.SpringBootBaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrudHttpServiceTest extends SpringBootBaseTest {

    @Autowired
    UserFacade userFacade;

    @AfterEach
    void removeTestCreatedUser() throws IOException {
        userFacade.removeUser("sample@email.com");
    }

    @Test
    public void loginSuccessTest() throws IOException {
        UserDTO user = userFacade.authUser("admin@email.com", "123");
        assertThat(user.getEmail()).isNotNull();
    }

    @Test
    public void createUserSuccessTest() {
        Exception exception = assertThrows(IOException.class, () -> userFacade.authUser("sample@email.com", "123456"));

        String expectedMessage = "1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }
}
