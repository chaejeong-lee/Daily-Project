package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/self")
public class SelfController {

    @GetMapping("/index")
    public String index(){return "self/index";}

    @GetMapping("/self_turtleneck")
    public String self_turtleneck(){return "self/self_turtleneck";}

    @GetMapping("/self_scoliosis")
    public String self_scoliosis(){return "self/self_scoliosis";}

    @GetMapping("/self_frozen_shoulder")
    public String self_frozen_shoulder(){return "self/self_frozen_shoulder";}

    @GetMapping("/self_disk")
    public String self_disk(){return "self/self_disk";}

    @GetMapping("/self_rcs")
    public String self_rcs(){return "self/self_rcs";}

    @GetMapping("/self_hand")
    public String self_hand(){return "self/self_hand";}

    @GetMapping("/self_roundshoulder")
    public String self_roundshoulder(){return "self/self_roundshoulder";}

    @GetMapping("/self_bowlegs")
    public String self_bowlegs(){return "self/self_bowlegs";}

    @GetMapping("/self_halluxValgus")
    public String self_halluxValgus(){return "self/self_halluxvalgus";}
}
