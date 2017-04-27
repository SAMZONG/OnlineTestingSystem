package com.mum.pm.user_module.controller;

import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.service.MailService;
import com.mum.pm.user_module.service.TestKeyService;
import com.mum.pm.user_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Chuang on 2017/4/26.
 */
@Controller
public class AssignTestController {
    @Autowired
    private TestKeyService testKeyService;
    @Autowired
    private UserService userService;
    @Autowired
    MailService mailService;

    @RequestMapping(value="/admin/assign-test",  method = RequestMethod.GET)
    public ModelAndView assignNewTest() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("testkey", new TestKey());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.setViewName("assign-test");
        return modelAndView;
    }

    @RequestMapping(value="/admin/assign-test",  method = RequestMethod.POST)
    public ModelAndView createNewTestKey(TestKey testKey, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Student student = userService.findStudentById(testKey.getStudentid());

        modelAndView.addObject("userName",   user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole",   "Admin");

        if(student == null){
           modelAndView.addObject("successMessage", "There is no such student with the id provided");
            modelAndView.addObject("testkey",testKey);
        }else{
            testKeyService.generateAndSaveTestKey(user.getId(), testKey.getStudentid(),testKey.getCategoryName());
            try {
                mailService.sendMail("noemail@gmail.com", "noemail@gmail.com", "Test", "Hello Manzil, This is Binesh Sah!");
            }catch(Exception e){

            }
            modelAndView.addObject("testkey", new TestKey());
            modelAndView.addObject("successMessage", "Test key has been generated successfully");
        }
        modelAndView.setViewName("assign-test");
        return modelAndView;
    }

}
