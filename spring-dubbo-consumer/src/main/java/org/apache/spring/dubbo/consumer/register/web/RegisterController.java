package org.apache.spring.dubbo.consumer.register.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.commons.dto.UserDTO;
import org.apache.spring.dubbo.consumer.login.web.LoginController;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.inter.register.api.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class RegisterController {

    @DubboReference
    RegisterService registerService;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(LoginController.class);


    @GetMapping("/")
    public String register(){
        return "registration";
    }

    @PostMapping("/")
        public String insertUser(@ModelAttribute UserDTO userDTO) throws IOException {
        Integer user = registerService.insertUser(userDTO.getEmail(), userDTO.getPassword());
        try {
            if (user == 1) {
                return "login";
            } else {
                log.error("APP-100-300", userDTO.getEmail());
                return "error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
