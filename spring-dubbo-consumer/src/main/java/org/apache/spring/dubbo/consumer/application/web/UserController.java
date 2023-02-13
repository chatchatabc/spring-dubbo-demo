package org.apache.spring.dubbo.consumer.application.web;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.spring.dubbo.consumer.application.common.vo.UserRegistrationVO;
import org.apache.spring.dubbo.port.UserFacade;
import org.apache.spring.dubbo.port.dto.UserDTO;
import org.apache.spring.dubbo.consumer.util.error.AppErrorFactory;
import org.apache.spring.dubbo.consumer.util.error.AppErrorLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.RedirectView;

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
    public String loginUser(String email, String password, Model model) {
        try {
            System.out.println(email);
            System.out.println(password);
            final UserDTO userDTO = userFacade.authUser(email, password);
            System.out.println(userDTO);
            if(userDTO.getEmail().isEmpty()){
                return "login";
            }
            model.addAttribute("user", userDTO);
            return "homepage";
        } catch (IOException e) {
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
    public String register(@ModelAttribute("user")UserRegistrationVO userRegistrationVO) throws IOException {
        if(!userRegistrationVO.getPassword().equals(userRegistrationVO.getMatchingPassword())){
            return "error";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userRegistrationVO.getEmail());
        userDTO.setPassword(userRegistrationVO.getPassword());
        userDTO.setUsername(userRegistrationVO.getUsername());

        UserDTO user = userFacade.registerUser(userDTO);

        try {

            if (user.equals("success")) {
                return "/login";
            } else {
                log.error("APP-100-300", userRegistrationVO.getEmail());
                return "error";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
