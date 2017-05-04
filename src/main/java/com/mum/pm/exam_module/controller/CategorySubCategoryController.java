package com.mum.pm.exam_module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by manzil on 5/3/2017.
 */
@Controller
@RequestMapping("/student")
public class CategorySubCategoryController {

    @RequestMapping(value="/category-subCategory", method = RequestMethod.GET)
    public String getCategorySubCategory(){
        return "category-subCategory";
    }
}
