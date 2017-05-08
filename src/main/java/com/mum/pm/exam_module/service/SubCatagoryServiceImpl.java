//package com.mum.pm.exam_module.service;
//
//import com.mum.pm.inventory_module.dao.CategoryDAO;
//import com.mum.pm.inventory_module.dao.SubCategoryDAO;
//import com.mum.pm.inventory_module.model.Category;
//import com.mum.pm.inventory_module.model.SubCategory;
//import com.mum.pm.question_module.dao.QuestionDAO;
//import com.mum.pm.question_module.model.Question;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by govin on 4/26/2017.
// */
//@Service
//public class SubCatagoryServiceImpl implements SubCatagoryService {
//
//    @Autowired
//    CategoryDAO catagory;
//    @Autowired
//    SubCategoryDAO subCategoryDAO;
//
//    //@Autowired
//   // QuestionDAO questionDAO;
//
//    @Override
//    public Iterable<Category> getAllCategory() {
//        return catagory.findAll();
//    }
//
//    @Override
//    public Iterable<SubCategory> getSubCategoryByCategory(Category category) {
//        return subCategoryDAO.findByCategory(category);
//    }
//
//
//  /*  @Override
//    public Iterable<Question> getAllQuestions() {
//        return questionDAO.findAll();
//    }*/
//
//}
