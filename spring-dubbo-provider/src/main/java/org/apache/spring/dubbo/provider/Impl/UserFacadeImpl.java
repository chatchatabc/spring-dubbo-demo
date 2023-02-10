package org.apache.spring.dubbo.provider.Impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.domain.service.CrudHttpService;
import org.apache.spring.dubbo.provider.util.error.AppErrorFactory;
import org.apache.spring.dubbo.provider.util.error.AppErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
@DubboService
public class UserFacadeImpl implements UserFacade {

    @Autowired
    CrudHttpService crudHttpService;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(UserFacadeImpl.class);

    @Override
    public Boolean authUser(UserDTO userDTO) throws IOException {
        String url = "http://localhost:8090/users?select=email,password&email=eq."+ userDTO.getEmail() +"&password=eq." + userDTO.getPassword();
        List<User> users = crudHttpService.parseFromGson(crudHttpService.get(url));
        if(users.size() == 1){
            return true;
        }else {
            log.error("APP-100-200");
            return false;
        }
    }

    @Override
    public String registerUser(UserDTO userDTO) throws IOException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        String url = "http://localhost:8090/users";
        String result = crudHttpService.post(url, crudHttpService.parseToGson(user));
        return result != null ? "success" : "fail";
    }

    public void removeUser(String email) throws IOException {
        String url = "http://localhost:8090/users?email=eq." + email;
        crudHttpService.delete(url);
    }

}
