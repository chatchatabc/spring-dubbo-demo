package org.apache.spring.dubbo.provider.Impl.domain;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.spring.dubbo.inter.LoginService;
import org.apache.spring.dubbo.provider.domain.model.User;
import org.apache.spring.dubbo.provider.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean getByEmail(String email, String password) {
       return userRepository.findByEmail(email, password) != null;
    }
}
