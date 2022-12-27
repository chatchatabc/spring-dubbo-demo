package org.apache.spring.dubbo.consumer.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.application.commons.dto.LoginDTO;
import org.apache.spring.dubbo.inter.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @DubboReference
    private LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String getByEmail(@RequestBody LoginDTO loginDTO) {
        Boolean user = loginService.getByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        if (user == true) {
            ResponseEntity.ok().build();
            return "homepage";
        } else {
            ResponseEntity.badRequest().build();
            return "error";
        }
    }
}
