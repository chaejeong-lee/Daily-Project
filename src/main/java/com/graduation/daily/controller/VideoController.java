package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video-diagnosis")
public class VideoController {

    @GetMapping("/index")
    public String index() {
        return "video-diagnosis/index";
    }

    @GetMapping("/video_turtleNeck_right")
    public String turtleNeckRight() {
        return "video-diagnosis/video_turtleNeck_right";
    }

    @GetMapping("/video_turtleNeck_left")
    public String turtleNeckLeft() {
        return "video-diagnosis/video_turtleNeck_left";
    }
}
