package com.mum.pm.user_module.controller;

import com.mum.pm.user_module.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 985191 on 4/27/2017.
 */

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value={"/student","/student/test"},  method = RequestMethod.GET)
    public ModelAndView addAccessKey() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-access-key");
        return modelAndView;
    }

    @RequestMapping(value={"/student/test"},  method = RequestMethod.POST)
    public ModelAndView postAccessKey() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-access-success");
        return modelAndView;
    }
}
