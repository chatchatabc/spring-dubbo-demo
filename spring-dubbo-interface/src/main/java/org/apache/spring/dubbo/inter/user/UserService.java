package org.apache.spring.dubbo.inter.user;

import java.io.IOException;

public interface UserService {

    Boolean findByEmail(String email, String password) throws IOException;

    String createUser( String username ,String email ,String password) throws IOException;

    void removeUser(String email) throws IOException;
}
