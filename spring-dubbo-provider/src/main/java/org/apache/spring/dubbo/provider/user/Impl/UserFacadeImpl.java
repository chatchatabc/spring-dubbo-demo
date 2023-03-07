package org.apache.spring.dubbo.provider.user.Impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.facade.UserFacade;
import org.apache.spring.dubbo.facade.dto.UserDTO;
import org.apache.spring.dubbo.provider.user.domain.model.User;
import org.apache.spring.dubbo.provider.user.domain.service.CrudHttpService;
import org.apache.spring.dubbo.provider.user.domain.specification.UserSpec;
import org.apache.spring.dubbo.provider.user.util.CodecUtils;
import org.apache.spring.dubbo.provider.user.util.error.AppErrorFactory;
import org.apache.spring.dubbo.provider.user.util.error.AppErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        UserDTO userDTO = new UserDTO();
        String url = "http://localhost:8090/users?select=id,email,password,salt,username&email=eq."+ email;
        crudHttpService.parseFromGson(crudHttpService.get(url)).stream()
                 .filter(user -> codecUtils.matches(password, user.getPassword(), user.getSalt()))
                 .forEach(user -> {
                     userDTO.setSerialVersionUID(user.getId());
                     userDTO.setEmail(user.getEmail());
                     userDTO.setPassword(user.getPassword());
                     userDTO.setUsername(user.getUsername());
                 });
         return userDTO;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws IOException {
        User user = new User();
        user.setEmail(userDTO.getEmail());

        if(userSpec.isEmailExist(user.getEmail())){
            throw new IOException("User already exists: " + user.getEmail());
        }

        if (userDTO.getPassword().isEmpty()){
            throw new IOException("Empty Password");
        }
            String salt = codecUtils.generateSalt();
            user.setPassword(codecUtils.hash(userDTO.getPassword(), salt));
            user.setUsername(userDTO.getUsername());
            user.setSalt(salt);
            String url = "http://localhost:8090/users?select=id,email,password,username";
            UserDTO userDTOReturn = new UserDTO();
            crudHttpService.parseFromGson(crudHttpService.post(url, crudHttpService.parseToGson(user)))
             .forEach(userReturn -> {
                userDTOReturn.setEmail(userReturn.getEmail());
                userDTOReturn.setPassword(userReturn.getPassword());
                userDTOReturn.setUsername(userReturn.getUsername());
            });
            return userDTOReturn;


    }

    public void removeUser(String email) throws IOException {
        String url = "http://localhost:8090/users?email=eq." + email;
        crudHttpService.delete(url);
    }

}
