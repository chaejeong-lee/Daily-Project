package com.graduation.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

    @GetMapping("/index")
    public String index() {
        return "disease/index";
    }

    @GetMapping("/disease_turtleneck")
    public String disease_turtleneck() {return "disease/disease_turtleneck";}

    @GetMapping("/disease_rcs")
    public String disease_rcs() {return "disease/disease_rcs";}

    @GetMapping("/disease_hand")
    public String disease_hand(){return "disease/disease_hand";}

    @GetMapping("/disease_halluxValgus")
    public String disease_halluxV(){return "disease/disease_halluxValgus";}

    @GetMapping("/disease_bowlegs")
    public String disease_bowlegs(){return "disease/disease_bowlegs";}

    @GetMapping("/disease_scoliosis")
    public String disease_scoliosis() {return "disease/disease_scoliosis";}

    @GetMapping("/disease_frozenshoulder")
    public String disease_frozenshoulder() {return "disease/disease_frozenshoulder";}

    @GetMapping("/disease_hivd")
    public String disease_hivd() {return "disease/disease_hivd";}

    @GetMapping("/disease_roundshoulder")
    public String disease_roundshoulder() {return "disease/disease_roundshoulder";}
}
