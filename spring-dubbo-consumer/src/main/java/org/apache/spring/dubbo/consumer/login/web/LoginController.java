package org.apache.spring.dubbo.consumer.login.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.login.commons.dto.LoginDTO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.inter.login.api.LoginService;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class LoginController {

    @DubboReference
    LoginService loginService;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(LoginController.class);
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/")
    public String getByEmail(@ModelAttribute LoginDTO loginDTO) throws IOException {
        Boolean user = loginService.getByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        try {
            if (user) {
                return "homepage";
            } else {
                log.error("APP-100-300", loginDTO.getEmail());
                return "error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
