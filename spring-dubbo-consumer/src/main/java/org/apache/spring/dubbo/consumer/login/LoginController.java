package org.apache.spring.dubbo.consumer.login;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.inter.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class LoginController {

    @DubboReference
    private LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String getByEmail(@ModelAttribute LoginDTO loginDTO) throws IOException {
//        System.out.println(loginDTO.getEmail());
//        System.out.println(loginDTO.getPassword());
//        return "login";
        Boolean user = loginService.getByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        try {
            if (user == true) {
                return "homepage";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
