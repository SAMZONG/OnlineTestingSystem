package com.mum.pm.exam_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.inventory_module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by manzil on 5/3/2017.
 */

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/subCategories/{id}", method = RequestMethod.GET)
    public ResponseEntity<Set<SubCategory>> getSubCategory(@PathVariable("id") int id) {
        Set<SubCategory> subCategories = categoryService.getAllSubCategoriesOfACategory(id);
        if (subCategories == null || subCategories.isEmpty()) {
            return new ResponseEntity<Set<SubCategory>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Set<SubCategory>>(subCategories, HttpStatus.OK);
    }
}
