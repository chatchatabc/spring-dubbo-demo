package org.apache.spring.dubbo.consumer.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.inter.MyUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @DubboReference
    private MyUserService myUserService;

    @GetMapping("/")
    public String login(){
        return myUserService.getByEmail("Anthony@email.com");
    }

}
