package com.mum.pm.question_module.controller;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.service.QuestionService;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;


/**
 * Created by tareman on 4/21/2017.
 */

@MultipartConfig
@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

        @RequestMapping(value= "/questionsById/{id}", method = RequestMethod.GET)
    public List<Question> getQuestionsById(@PathVariable int id){
        return (List<Question>) questionService.getQuestionsBySubCategoryID(id);
    }

    @RequestMapping(value="/question/add-question",  method = RequestMethod.GET)
    public ModelAndView addNewStudent() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("question", new Question());
        modelAndView.addObject("userRole", "Admin");


        modelAndView.setViewName("add-question");
        return modelAndView;
    }

    @RequestMapping(value="/question/add-question",  method = RequestMethod.POST)
    public ModelAndView createNewStudent(Question question, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        questionService.save(question);

        modelAndView.addObject("question", new Question());
        modelAndView.addObject("successMessage", "Question add successfully");

        modelAndView.setViewName("add-question");
        return modelAndView;
    }


    @RequestMapping(value = "/question-entry", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("questionForm", new Question());

        return "questionentry";
    }

    @RequestMapping(value = "/question-entry", method = RequestMethod.POST)
    public String registration(@ModelAttribute("questionForm") Question questionForm, BindingResult bindingResult, Model model) {
        //  userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "questionentry";
        }
        questionService.save(questionForm);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.GET)
    public String fileUplead(Model model) {
        model.addAttribute("questionForm", new Question());

        return "fileupload";
    }


}
