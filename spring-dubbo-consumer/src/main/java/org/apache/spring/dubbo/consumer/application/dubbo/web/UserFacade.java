package org.apache.spring.dubbo.consumer.application.dubbo.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.port.UserPort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

public class UserFacade {
    @DubboReference
    UserPort userPort;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(UserFacade.class);

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String authUser(@ModelAttribute UserDTO userDTO) throws IOException {

        Boolean user = userPort.findByEmail(userDTO);
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

    @GetMapping("/registration")
    public String register(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") UserDTO userDTO) throws IOException {
        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            return "error";
        }

        String user = userPort.createUser(userDTO);

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
