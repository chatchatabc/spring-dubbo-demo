package org.apache.spring.dubbo.consumer.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/homepage")
    public String home(){
        return "homepage";
    }
}
