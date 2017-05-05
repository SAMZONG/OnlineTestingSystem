package com.mum.pm.quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @GetMapping("/test")
    public String quize() {
        return "test";
    }

}