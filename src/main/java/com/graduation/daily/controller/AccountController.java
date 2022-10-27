package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "account/signup";
    }

    @GetMapping("/findID")
    public String findID(){ return "account/findID"; }

    @GetMapping("/findPW")
    public String findPW(){ return "account/findPW"; }
}
