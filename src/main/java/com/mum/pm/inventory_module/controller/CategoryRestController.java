package com.mum.pm.inventory_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.inventory_module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by manzil on 4/25/2017.
 */
@RestController
@RequestMapping("/category")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/getCategoryById/{id}", method= RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
        Category category=categoryService.getCategoryById(id);
        if(category==null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value="/getCategoryByName/{name}", method= RequestMethod.GET)
    public ResponseEntity<Category> getCategoryByName(@PathVariable("name") String name){
        Category category=categoryService.getCategoryByName(name);
        if(category==null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value="/getAllCategories", method= RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        if(categories.isEmpty()){
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
    }

    @RequestMapping(value="/saveCategory", method= RequestMethod.POST)
    public ResponseEntity<Void> saveCategory(@RequestBody Category category){
        if(categoryService.isCategoryExist(category)){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        categoryService.saveCategory(category);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value="/updateCategory/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category){
        Category currentCategory= categoryService.getCategoryById(id);
        if(currentCategory==null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setCategoryId(id);
        currentCategory.setCategoryName(category.getCategoryName());
        Set<SubCategory> subCategories= (Set<SubCategory>) category.getSubCategories();
        System.out.println(subCategories);
        if(subCategories!=null) {
            for (SubCategory s : subCategories) {
                currentCategory.addSubCategory(s);
            }
        }
        categoryService.saveCategory(currentCategory);
        return  new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }

    @RequestMapping(value="/updateCategoryName/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Category> updateCategoryName(@PathVariable("id") int id, @RequestBody Category category){
        Category currentCategory= categoryService.getCategoryById(id);
        if(currentCategory==null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setCategoryId(id);
        currentCategory.setCategoryName(category.getCategoryName());
        categoryService.saveCategory(currentCategory);
        return  new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }

    @RequestMapping(value="/deleteCategory/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id){
        Category category= categoryService.getCategoryById(id);
        if(category==null){
            return new ResponseEntity<Category> (HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<Category> (HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/getSubCategories/{id}", method= RequestMethod.GET)
    public ResponseEntity<Set<SubCategory>> getSubCategory(@PathVariable("id") int id){
        Set<SubCategory> subCategories= categoryService.getAllSubCategoriesOfACategory(id);
        if(subCategories==null || subCategories.isEmpty()){
            return new ResponseEntity<Set<SubCategory>> (HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Set<SubCategory>> (subCategories,HttpStatus.OK);
    }





    @RequestMapping("/update/{id}")
    public String save(){
        Category c= new Category();
        c.setCategoryName("PHP");
        SubCategory sc= new SubCategory();
        sc.setSubCategoryName("Script");
        c.addSubCategory(sc);
        categoryService.saveCategory(c);
        return "Data Saved Successfully";


    }






}
