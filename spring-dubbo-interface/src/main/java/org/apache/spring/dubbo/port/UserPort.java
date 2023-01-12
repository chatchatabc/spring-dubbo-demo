package org.apache.spring.dubbo.port;

import org.apache.spring.dubbo.port.dto.UserDTO;

import java.io.IOException;

public interface UserPort {


    Boolean findByEmail(UserDTO userDTO) throws IOException;

    String createUser(UserDTO userDTO) throws IOException;

    void removeUser(String email) throws IOException;
}
