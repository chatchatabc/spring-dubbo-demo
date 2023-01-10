package org.apache.spring.dubbo.consumer.register.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.commons.dto.UserDTO;
import org.apache.spring.dubbo.consumer.login.web.LoginController;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.apache.spring.dubbo.inter.register.api.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class RegisterController {

    @DubboReference
    RegisterService registerService;

    private static final AppErrorLogger log = AppErrorFactory.getLogger(LoginController.class);


    @GetMapping("/registration")
    public String register(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String insertUser(@ModelAttribute("user") UserDTO userDTO) throws IOException {
        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            return "error";
        }

        String user = registerService.insertUser(userDTO.getUsername(), userDTO.getEmail(),userDTO.getPassword());

        try {
            if (user.equals("success")) {
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
