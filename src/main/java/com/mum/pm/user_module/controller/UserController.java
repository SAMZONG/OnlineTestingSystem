package com.mum.pm.user_module.controller;

import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.model.User;
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
 * Created by Chuang on 2017/4/27.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/admin/add-student",  method = RequestMethod.GET)
    public ModelAndView addNewStudent() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.setViewName("add-student");
        return modelAndView;
    }

    @RequestMapping(value="/admin/add-student",  method = RequestMethod.POST)
    public ModelAndView createNewStudent(Student student, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        userService.findStudentById(student.getStudentId());

        modelAndView.addObject("userName",   user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole",   "Admin");

        if(userService.findStudentById(student.getStudentId()) != null){
            modelAndView.addObject("successMessage", "This student is already in the system");
            modelAndView.addObject("student",student);
        }else{
            userService.saveStudent(student);
            modelAndView.addObject("student", new Student());
            modelAndView.addObject("successMessage", "Student add successfully");
        }
        modelAndView.setViewName("add-student");
        return modelAndView;
    }


    @RequestMapping(value="/admin/add-user",  method = RequestMethod.GET)
    public ModelAndView addNewUser() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.addObject("newuser", new User());
        modelAndView.addObject("allroles", userService.findAllRole());
        modelAndView.setViewName("add-user");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add-user", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User newUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole", "Admin");

        User userExists = userService.findUserByEmail(newUser.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(newUser);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("newuser", new User());
            modelAndView.setViewName("add-user");
        }
        return modelAndView;
    }
}
