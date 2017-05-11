package com.mum.pm.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by manzil on 5/9/2017.
 */
@Controller
public class ReportController {

    @RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
    public String showReports() {
        return "report";
    }
}
