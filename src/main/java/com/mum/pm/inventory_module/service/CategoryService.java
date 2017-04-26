package com.mum.pm.inventory_module.service;

import com.mum.pm.inventory_module.model.Category;

import java.util.List;

/**
 * Created by manzi on 4/25/2017.
 */
public interface CategoryService {
    public void saveCategory(Category category);
    public Category getCategoryById(int id);
    public Category getCategoryByName(String name);
    public void deleteCategory(int id);
    public List<Category> getAllCategories();

}
