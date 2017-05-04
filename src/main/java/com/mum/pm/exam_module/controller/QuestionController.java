package com.mum.pm.exam_module.controller;

/**
 * Created by govin on 4/26/2017.
 */

import com.mum.pm.exam_module.service.QuestionService;
import com.mum.pm.exam_module.service.SubCatagoryService;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.question_module.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/questions")
public class QuestionController {

   @Autowired
    QuestionService questionService;

   @Autowired
   SubCatagoryService subCatagoryService;

    @ResponseBody
    @RequestMapping(value="/getSubCategories", method= RequestMethod.GET)
    public List<SubCategory> showSubcatagoryList()
    {
       //return questionSubService.getAllSubCategory();
       // System.out.println("In sub category-------------");
        Iterable<Category> catagoriesList= subCatagoryService.getAllCategory();
        for (Category category : catagoriesList) {
            System.out.println(category.getCategoryName() + " --- " + category.getCategoryId());
            Iterable<SubCategory> subCatagoriesList = subCatagoryService.getSubCategoryByCategory(category);
            for (SubCategory subcategory : subCatagoriesList) {
                System.out.println("===============" + subcategory.getSubCategoryName());

             Iterable<Question> questionsList = questionService.getQuestionsBySubCategoryID(subcategory.getSubCategoryId());
                for (Question question : questionsList) {
                    System.out.println(".............................." + question.getQuestion_description());
                }
            }
        }

        return null;
    }
    /*@ResponseBody
    public List<Question> showAllQuestion()
    {
        return null;
    }*/

    @RequestMapping(value= "/questionsById/{id}", method = RequestMethod.GET)
    public List<Question> getQuestionsById(@PathVariable int id){
        return (List<Question>) questionService.getQuestionsBySubCategoryID(id);
    }






}
