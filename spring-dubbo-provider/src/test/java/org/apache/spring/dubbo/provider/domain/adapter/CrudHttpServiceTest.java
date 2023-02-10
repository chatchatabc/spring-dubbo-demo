package org.apache.spring.dubbo.provider.domain.adapter;


import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@email.com");
        userDTO.setPassword("123");
        Boolean val = userFacade.authUser(userDTO);

        assertThat(val).isTrue();
    }

    @Test
    public void createUserSuccessTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("sample");
        userDTO.setEmail("sample@email.com");
        userDTO.setPassword("123456");
        Exception exception = assertThrows(IOException.class, () -> userFacade.authUser(userDTO));

        String expectedMessage = "1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }
}
