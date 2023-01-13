package org.apache.spring.dubbo.provider.adapter;

import okhttp3.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.spring.dubbo.port.UserPort;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.infra.okhttp3.NetworkPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@DubboService
public class UserAdapter implements UserPort {

    @Autowired
    NetworkPort networkPort;

    @Override
    public Boolean findByEmail(UserDTO userDTO) throws IOException {
        String url = "http://localhost:8090/users?select=email,password&email=eq."+ userDTO.getEmail() +"&password=eq." + userDTO.getPassword();
        List<User> users = networkPort.parseFromGson(networkPort.get(url));
        System.out.println(users);
        return users.size() == 1;
    }

    @Override
    public String createUser(UserDTO userDTO) throws IOException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        String url = "http://localhost:8090/users";
        String result = networkPort.post(url, networkPort.parseToGson(user));
        return result != null ? "success" : "fail";
    }

    public void removeUser(String email) throws IOException {
        String url = "http://localhost:8090/users?email=eq." + email;
        networkPort.delete(url);
    }

}
