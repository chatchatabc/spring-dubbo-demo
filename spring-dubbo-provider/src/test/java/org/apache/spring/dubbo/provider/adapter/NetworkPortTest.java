package org.apache.spring.dubbo.provider.adapter;


import org.apache.spring.dubbo.port.UserPort;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.provider.SpringBootBaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NetworkPortTest extends SpringBootBaseTest {

    @Autowired
    UserPort userPort;

    @AfterEach
    void removeTestCreatedUser() throws IOException {
        userPort.removeUser("sample@email.com");
    }

    @Test
    public void loginSuccessTest() throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("admin@email.com");
        userDTO.setPassword("123");
        Boolean val = userPort.findByEmail(userDTO);

        assertThat(val).isTrue();
    }

    @Test
    public void createUserSuccessTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("sample");
        userDTO.setEmail("sample@email.com");
        userDTO.setPassword("123456");
        Exception exception = assertThrows(IOException.class, () -> userPort.createUser(userDTO));

        String expectedMessage = "1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }
}
