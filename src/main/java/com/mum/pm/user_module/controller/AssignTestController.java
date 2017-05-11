package com.mum.pm.user_module.controller;

import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.service.TestKeyService;
import com.mum.pm.user_module.service.UserService;
import com.mum.pm.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Chuang on 2017/4/26.
 */
@RestController
//@RequestMapping("/admin")
public class AssignTestController {
    @Autowired
    MailService mailService;
    @Autowired
    private TestKeyService testKeyService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/student/getAvailableStudent", method = RequestMethod.GET)
    public List<Student> getAvailableStudent() {
        return userService.findAvailableStudent();
    }

    @RequestMapping(path = "/student/getAllStudent", method = RequestMethod.GET)
    public List<Student> getAllStudent() {
        return userService.findAllStudent();
    }

    @RequestMapping(path = "/student/deleteStudent", method = RequestMethod.POST)
    public Student deleteStudent(@RequestBody Student student) {
        Student studentDB = userService.findStudentById(student.getStudentId());
        if (studentDB != null) {
            userService.inactiveStudent(studentDB);
        }
        return student;
    }


    @RequestMapping(path = "/admin/assign-test", method = RequestMethod.GET)
    public ModelAndView goHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.setViewName("assign-test");
        return modelAndView;
    }

    @RequestMapping(path = "/admin/all-student", method = RequestMethod.GET)
    public ModelAndView allStudent() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.setViewName("all-student");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/assigntest", method = RequestMethod.POST)
    public Student createAccessCode(@RequestBody Student student) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            Student studentDB = userService.findStudentById(student.getStudentId());

            TestKey testKey = new TestKey();

            if (studentDB.isActive()) {
                String accessCode = testKeyService.generateAndSaveTestKey(user.getId(), studentDB.getStudentId());
                mailService.sendMail(student, accessCode);
            } else {
                //do nothing if this student is not active, inactive should not show on the front page
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

}
