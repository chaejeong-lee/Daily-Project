package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DailyController {

    @GetMapping
    public String index() {
        return "index";
    }
}
