package org.apache.spring.dubbo.consumer.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@SuppressWarnings("PlaceholderCountMatchesArgumentCount")
@Controller
public class UserController {
    @DubboReference
    UserFacade userFacade;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model) throws IOException {
        try {
            UserDTO user = userFacade.authUser(email, password);
            model.addAttribute("user", user);
           return "homepage";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDTO userDTO) throws IOException {
        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            return "error";
        }

        String user = userFacade.registerUser(userDTO);

        try {
            if (user.equals("success")) {
                return "/login";
            } else {
                log.error("APP-100-300", userDTO.getEmail());
                return "error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
