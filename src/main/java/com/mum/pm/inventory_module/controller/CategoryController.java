package com.mum.pm.inventory_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.inventory_module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by manzil on 4/25/2017.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/addCategory")
    public String categoryForm(Model model) {
        model.addAttribute("category",new Category());
        return "category";
    }

    @PostMapping("/addCategory")
    public void categorySubmit(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
    }


}
