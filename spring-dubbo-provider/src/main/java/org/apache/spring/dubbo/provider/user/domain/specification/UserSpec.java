package org.apache.spring.dubbo.provider.user.domain.specification;

import java.io.IOException;

public interface UserSpec {
    /**
     * check if user exists
     */
    Boolean isEmailExist(String email) throws IOException;
}
