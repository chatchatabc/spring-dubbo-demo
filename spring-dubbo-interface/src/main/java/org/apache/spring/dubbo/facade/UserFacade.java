package org.apache.spring.dubbo.facade;

import org.apache.spring.dubbo.facade.dto.UserDTO;

import java.io.IOException;

public interface UserFacade {

    UserDTO authUser(String email, String password) throws IOException;

    UserDTO registerUser(UserDTO userDTO) throws IOException;

    void removeUser(String email) throws IOException;

}
