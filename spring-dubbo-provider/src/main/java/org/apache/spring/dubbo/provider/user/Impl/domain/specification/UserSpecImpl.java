package org.apache.spring.dubbo.provider.user.Impl.domain.specification;

import org.apache.spring.dubbo.provider.user.domain.service.CrudHttpService;
import org.apache.spring.dubbo.provider.user.domain.specification.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserSpecImpl implements UserSpec {

    CrudHttpService crudHttpService;

    @Autowired
    public UserSpecImpl(CrudHttpService crudHttpService) {
        this.crudHttpService = crudHttpService;
    }

    /**
     * check if user exists implementation
     */
    public Boolean isEmailExist(String email) throws IOException {
        String url = "http://localhost:8090/users?select=email&email=eq."+ email;
        return !crudHttpService.parseFromGson(crudHttpService.get(url)).isEmpty();
    }
}
