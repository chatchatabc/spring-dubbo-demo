package org.apache.spring.dubbo.consumer.login.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.commons.dto.UserDTO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.inter.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@SuppressWarnings("PlaceholderCountMatchesArgumentCount")
@Controller
public class LoginController {

    @DubboReference
    UserService userService;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/")
    public String authUser(@ModelAttribute UserDTO userDTO) throws IOException {

        Boolean user = userService.findByEmail(userDTO.getEmail(), userDTO.getPassword());
        try {
            if (user) {
                return "homepage";
            } else {
                log.error("APP-100-300", userDTO.getEmail());
                return "error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
