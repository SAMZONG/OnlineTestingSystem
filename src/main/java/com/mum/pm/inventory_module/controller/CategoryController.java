package com.mum.pm.inventory_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.inventory_module.service.CategoryService;
import com.mum.pm.user_module.model.Student;
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

/**
 * Created by manzil on 4/25/2017.
 */
@Controller
//@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/admin/add-category",  method = RequestMethod.GET)
    public ModelAndView addNewCategory() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("userRole", "Admin");

        modelAndView.setViewName("add-category");
        return modelAndView;
    }

    @RequestMapping(value="/admin/add-category",  method = RequestMethod.POST)
    public ModelAndView createNewCategory(Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        modelAndView.addObject("userName",   user.getName() + " " + user.getLastName());
        modelAndView.addObject("userRole",   "Admin");

        if(categoryService.getCategoryByName(category.getCategoryName()) != null){
            modelAndView.addObject("successMessage", "This category is already in the system");
            modelAndView.addObject("category",category);
        }else{
            categoryService.saveCategory(category);
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("successMessage", "Category added successfully");
        }
        modelAndView.setViewName("add-category");
        return modelAndView;
    }
}




