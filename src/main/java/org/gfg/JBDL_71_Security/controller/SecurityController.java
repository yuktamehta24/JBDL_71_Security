package org.gfg.JBDL_71_Security.controller;

import org.gfg.JBDL_71_Security.model.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/spring-security")
    public String springSecurity() {
        return "Spring security";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser user =  (MyUser) authentication.getPrincipal();
        return "hello ".concat(user.getUsername());

    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @PostMapping("/postAPI")
    public String postAPI() {
        return "postAPI";
    }

}
