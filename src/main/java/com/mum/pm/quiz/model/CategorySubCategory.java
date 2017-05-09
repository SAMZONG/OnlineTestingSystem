package com.mum.pm.quiz.model;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manzil on 5/6/2017.
 */
public class CategorySubCategory {

    private Category category;
    private List<SubCategory> subCategories;
    private String accessKey;

    public CategorySubCategory(){
        subCategories=new ArrayList<SubCategory>();
    }

    public Category getCategory() {
        return category;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategorySubCategory{" +
                "category=" + category +
                ", subCategories=" + subCategories +
                '}';
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }


}
