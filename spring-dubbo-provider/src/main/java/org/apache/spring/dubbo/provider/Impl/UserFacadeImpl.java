package org.apache.spring.dubbo.provider.Impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.facade.UserFacade;
import org.apache.spring.dubbo.facade.dto.UserDTO;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.domain.service.CrudHttpService;
import org.apache.spring.dubbo.provider.domain.specification.UserSpec;
import org.apache.spring.dubbo.provider.util.CodecUtils;
import org.apache.spring.dubbo.provider.util.error.AppErrorFactory;
import org.apache.spring.dubbo.provider.util.error.AppErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        System.out.println(userDTO);
         return userDTO;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws IOException {
        String salt = codecUtils.generateSalt();
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(codecUtils.hash(userDTO.getPassword(), salt));
        user.setUsername(userDTO.getUsername());
        user.setSalt(salt);
        if(!userSpec.isEmailExist(user.getEmail())){
            throw new IOException("User already exists: " + user.getEmail());
        }else{
            UserDTO userDTOReturn = new UserDTO();
            String url = "http://localhost:8090/users";
            if(crudHttpService.post(url, crudHttpService.parseToGson(user)) == 201){
                userDTOReturn.setSerialVersionUID(user.getId());
                userDTOReturn.setEmail(user.getEmail());
                userDTOReturn.setPassword(user.getPassword());
                userDTOReturn.setUsername(user.getUsername());
                return userDTOReturn;
            } else {
                throw new IOException("Something went wrong");
            }

        }

    }

    public void removeUser(String email) throws IOException {
        String url = "http://localhost:8090/users?email=eq." + email;
        crudHttpService.delete(url);
    }

}
