package org.apache.spring.dubbo.provider.Impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.domain.service.CrudHttpService;
import org.apache.spring.dubbo.provider.domain.specification.UserSpec;
import org.apache.spring.dubbo.provider.util.CodecUtils;
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

    final CrudHttpService crudHttpService;
    final CodecUtils codecUtils;

    final UserSpec userSpec;

    @Autowired
    private UserFacadeImpl(CodecUtils codecUtils, CrudHttpService crudHttpService, UserSpec userSpec) {
        this.codecUtils = codecUtils;
        this.crudHttpService = crudHttpService;
        this.userSpec = userSpec;
    }

    private static final AppErrorLogger log = AppErrorFactory.getLogger(UserFacadeImpl.class);

    @Override
    public UserDTO authUser(String email, String password) throws IOException {
        String url = "http://localhost:8090/users?select=email,password&email=eq."+ email +"&password=eq." + password;
        return crudHttpService.parseFromGson(crudHttpService.get(url))
                .stream().map(user -> codecUtils.matches(password, user.getPassword(), user.getSalt())).collect(UserDTO::new, UserDTO::setUsername, UserDTO::setPassword, UserDTO::setEmail, UserDTO::setSalt);

    }

    @Override
    public String registerUser(UserDTO userDTO) throws IOException {
        String salt = codecUtils.generateSalt();
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(codecUtils.hash(userDTO.getPassword(), salt));
        user.setUsername(userDTO.getUsername());
        user.setSalt(salt);
        if(userSpec.isEmailExist(user.getEmail())){
            throw new IOException("User already exists: " + user.getEmail());
        }else{
            String url = "http://localhost:8090/users";
            String result = crudHttpService.post(url, crudHttpService.parseToGson(user));
            return result.isEmpty() ? "fail" : "success";
        }

    }

    public void removeUser(String email) throws IOException {
        String url = "http://localhost:8090/users?email=eq." + email;
        crudHttpService.delete(url);
    }

}
