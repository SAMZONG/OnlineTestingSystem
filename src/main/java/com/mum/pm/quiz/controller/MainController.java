package com.mum.pm.quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/student/exam/{accessKey}")
    public String getCategorySubCategory(@PathVariable String accessKey, Model model) {
        model.addAttribute("accessKey", accessKey);
        return "exam";
    }

    @GetMapping("/student/done")
    public String done() {
        return "done";
    }

}