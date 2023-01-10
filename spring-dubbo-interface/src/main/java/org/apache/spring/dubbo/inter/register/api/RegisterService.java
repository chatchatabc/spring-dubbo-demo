package org.apache.spring.dubbo.inter.register.api;

import java.io.IOException;

public interface RegisterService {

    String insertUser( String username ,String email ,String password) throws IOException;
}
