package org.apache.spring.dubbo.consumer.user.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.user.application.common.vo.UserVO;
import org.apache.spring.dubbo.consumer.user.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.user.util.error.AppErrorLogger;
import org.apache.spring.dubbo.facade.UserFacade;
import org.apache.spring.dubbo.facade.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import java.io.IOException;

@SuppressWarnings("PlaceholderCountMatchesArgumentCount")
@Controller
public class UserController {
    @DubboReference
    UserFacade userFacade;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(UserController.class);

    @GetMapping("/")
    public Mono<Rendering> login() {
        return Mono.just(Rendering.view("login").build());
    }
    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public Mono<Rendering> loginUser(UserVO userVO, Model model) throws IOException {
        if (userVO.getEmail().isEmpty() || userVO.getPassword().isEmpty()){
            return Mono.just(Rendering.view("login").build());
        }
        try {
            System.out.println(userVO.getEmail());
            System.out.println(userVO.getPassword());
            final UserDTO userDTO = userFacade.authUser(userVO.getEmail(), userVO.getPassword());
            System.out.println(userDTO);
            if(userDTO.getEmail().isEmpty()){
                return Mono.just(Rendering.view("login").build());
            }
            model.addAttribute("user", userDTO);
            return Mono.just(Rendering.view("homepage").build());
        } catch (Exception e) {
            throw new IOException();
        }


    }

    @GetMapping("/register")
    public Mono<Rendering> register(){
        return Mono.just(Rendering.view("registration").build());
    }

    @PostMapping(value = "/register-submit", consumes = "application/x-www-form-urlencoded")
    public Mono<Rendering> register(UserVO userVO, Model model) throws IOException {
        if (userVO.getEmail().isEmpty() || userVO.getPassword().isEmpty()){
            return Mono.just(Rendering.view("registration").build());
        }

        if(!userVO.getPassword().equals(userVO.getMatchingPassword())){
            return Mono.just(Rendering.view("registration").build());
        }

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(userVO.getEmail());
            userDTO.setPassword(userVO.getPassword());
            userDTO.setUsername(userVO.getUsername());
            model.addAttribute("user", userFacade.registerUser(userDTO));
            return Mono.just(Rendering.view("homepage").build());

        } catch (Exception e) {
            throw new IOException();
        }
    }
}
