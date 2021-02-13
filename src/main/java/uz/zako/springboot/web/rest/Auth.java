package uz.zako.springboot.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class Auth {
    @GetMapping("/")
    public String test(){
        System.out.println("ok");
        return "index";
    }
}
