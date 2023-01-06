package org.apache.spring.dubbo.inter.login.api;

import java.io.IOException;

public interface LoginService {

   Boolean getByEmail(String email, String password) throws IOException;

}
