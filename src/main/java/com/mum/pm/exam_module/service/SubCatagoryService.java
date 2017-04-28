package com.mum.pm.exam_module.service;

//import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.question_module.model.Question;

/**
 * Created by govin on 4/26/2017.
 */
public interface SubCatagoryService {

    public Iterable<Category> getAllCategory();

    public Iterable<SubCategory> getSubCategoryByCategory(Category category);
   // public Iterable<Question> getAllQuestions();
}
