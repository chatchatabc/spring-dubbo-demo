package org.apache.spring.dubbo.consumer.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.application.common.vo.UserVO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public Mono<Rendering> loginUser(UserVO userVO, Model model) {
        if (userVO.getEmail().isEmpty() || userVO.getPassword().isEmpty()){
            return Mono.just(Rendering.view("login").build());
        } else {
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
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return Mono.just(Rendering.view("error").build());
            }
        }

    }

    @GetMapping("/register")
    public Mono<Rendering> register(){
        return Mono.just(Rendering.view("registration").build());
    }

    @PostMapping(value = "/register-submit", consumes = "application/x-www-form-urlencoded")
    public String register(UserVO userVO, Model model) throws IOException {
        if(!userVO.getPassword().equals(userVO.getMatchingPassword())){
            return "error";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userVO.getEmail());
        userDTO.setPassword(userVO.getPassword());
        userDTO.setUsername(userVO.getUsername());

        UserDTO user = userFacade.registerUser(userDTO);

        try {

            if (user.equals("success")) {
                return "/login";
            } else {
                log.error("APP-100-300", userVO.getEmail());
                return "error";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
