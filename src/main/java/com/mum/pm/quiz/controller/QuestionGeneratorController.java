package com.mum.pm.quiz.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.quiz.model.CategorySubCategory;
import com.mum.pm.quiz.service.QuestionsGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by manzil on 5/6/2017.
 */
@Controller
@RequestMapping("/student")
public class QuestionGeneratorController {

    @Autowired
    QuestionsGeneratorService questionsGeneratorService;

    @RequestMapping(value="/exam", method= RequestMethod.POST)
    @ResponseBody
    public CategorySubCategory generateExamPage(@RequestBody CategorySubCategory categorySubCategory) {
        System.out.println("" + categorySubCategory);

        return categorySubCategory;

    }
}
