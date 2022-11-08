package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stretch")
public class StretchController {

    @GetMapping("/index")
    public String index() {
        return "stretch/index";
    }
}
