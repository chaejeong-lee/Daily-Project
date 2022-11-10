package com.graduation.daily.controller;

import com.graduation.daily.model.Survey;
import com.graduation.daily.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/self")
public class SelfController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping("/index")
    public String index() {
        return "self/index";
    }

    @GetMapping("/self_turtleneck")
    public String self_turtleneck(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_turtleneck";
    }

    @GetMapping("/self_scoliosis")
    public String self_scoliosis(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_scoliosis";
    }

    @GetMapping("/self_frozen_shoulder")
    public String self_frozen_shoulder(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_frozen_shoulder";
    }

    @GetMapping("/self_disk")
    public String self_disk(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_disk";
    }

    @GetMapping("/self_rcs")
    public String self_rcs(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_rcs";
    }

    @GetMapping("/self_hand")
    public String self_hand(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_hand";
    }

    @GetMapping("/self_roundshoulder")
    public String self_roundshoulder(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_roundshoulder";
    }

    @GetMapping("/self_bowlegs")
    public String self_bowlegs(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_bowlegs";
    }

    @GetMapping("/self_halluxValgus")
    public String self_halluxValgus(Model model, @RequestParam(required = false) Long id) {
        List<Survey> surveys = surveyRepository.findByType(id);
        model.addAttribute("surveys", surveys);
        return "self/self_halluxvalgus";
    }
}
