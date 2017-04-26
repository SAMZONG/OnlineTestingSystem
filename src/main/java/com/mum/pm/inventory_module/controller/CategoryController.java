package com.mum.pm.inventory_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by manzil on 4/25/2017.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/getCategoryById/{id}", method= RequestMethod.GET)
    public Category getCategoryById(@PathVariable("id") int id){
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value="/getCategoryByName/{name}", method= RequestMethod.GET)
    public Category getCategoryByName(@PathVariable("name") String name){
        return categoryService.getCategoryByName(name);
    }

    @RequestMapping(value="/updateCategory/{id}", method= RequestMethod.GET)
    public void updateCategory(@PathVariable("id") int id){
        Category category=new Category();
        category.setCategoryId(id);
        categoryService.saveCategory(category);
    }

    @RequestMapping(value="/deleteCategory/{id}", method= RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value="/saveCategory", method= RequestMethod.POST)
    public void saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
    }

    @RequestMapping(value="/getAllCategories", method= RequestMethod.GET)
    public void saveCategory(){
        categoryService.getAllCategories();
    }






}
