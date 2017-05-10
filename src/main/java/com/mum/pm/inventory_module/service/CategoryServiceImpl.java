package com.mum.pm.inventory_module.service;

import com.mum.pm.inventory_module.dao.CategoryDAO;
import com.mum.pm.inventory_module.dao.SubCategoryDAO;
import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by manzil on 4/25/2017.
 */
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    SubCategoryDAO subCategoryDAO;

    @Override
    public void saveCategory(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public Category getCategoryById(int id) {

        return categoryDAO.findOne(id);
    }

    @Override
    public SubCategory getSubCategoryById(int id) {

        return subCategoryDAO.findOne(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDAO.findCategoryByCategoryName(name);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryDAO.findAll();
    }

    @Override
    public boolean isCategoryExist(Category category) {
        return categoryDAO.exists(category.getCategoryId());
    }

    @Override
    public Set<SubCategory> getAllSubCategoriesOfACategory(int id) {
       return (Set<SubCategory>) subCategoryDAO.getSubCategoriesByCategory_CategoryId(id);
    }
    @Override
    public Category setCategoryDisable(Category category){
        category.setActive(0);
        return categoryDAO.save(category);
    }

    @Override
    public SubCategory setSubCategoryDisable(SubCategory subCategory){
        subCategory.setActive(0);
        return subCategoryDAO.save(subCategory);
    }


    @Override
    public void saveSubCategory(Category category, SubCategory subcategory) {
        subcategory.setActive(1);
        category.addSubCategory(subcategory);
        subCategoryDAO.save(subcategory);
    }

    @Override
    public SubCategory getSubCategoryByName(String name) {
        return subCategoryDAO.findSubCategoryBySubCategoryName(name);
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryDAO.findByCategoryActive(1);
    }

    @Override
    public List<SubCategory> getAllActiveSubCategories(int id) {
        return subCategoryDAO.findBySubCategoryActive(1, id);
    }
}
