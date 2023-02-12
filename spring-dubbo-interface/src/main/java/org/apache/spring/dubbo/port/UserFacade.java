package org.apache.spring.dubbo.port;

import org.apache.spring.dubbo.port.dto.UserDTO;

import java.io.IOException;

public interface UserFacade {

    UserDTO authUser(String email, String password) throws IOException;

    String registerUser(UserDTO userDTO) throws IOException;

    void removeUser(String email) throws IOException;

}
