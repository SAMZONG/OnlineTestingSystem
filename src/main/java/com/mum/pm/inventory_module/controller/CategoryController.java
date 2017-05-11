package com.mum.pm.inventory_module.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import com.mum.pm.inventory_module.service.CategoryService;
import com.mum.pm.user_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    int categoryID = 0;

    @RequestMapping(value = "/admin/add-category", method = RequestMethod.GET)
    public ModelAndView addNewCategory(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        //List<Category> categoryList = categoryService.getAllCategories();

        List<Category> categoryList = categoryService.getAllActiveCategories();
        model.addAttribute("categoryList", categoryList);
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("category", new Category());
        //modelAndView.addObject("userRole", "Admin");

        //modelAndView.setViewName("add-category");
        modelAndView.setViewName("add-category");
        return modelAndView;

    }
    /*


    @RequestMapping(value = "/addnewcheckingentry", method = RequestMethod.GET)
	public String showNewCheckingEntryForm(
			@Valid @ModelAttribute("tmhistory") TMHistory tmHistory, Model model) {
		model.addAttribute("checkingType", CheckingType.values());
		List<Student> students = studentService.getAllStudent();
		model.addAttribute("students", students);
		List<Building> buildings = buildingService.getAllBuildings();
		model.addAttribute("buildings", buildings);
		return "tmchecker/addNewCheckingEntry";
	}


     */

    @RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
    public String createNewCategory(Category category, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        List<Category> categoryList = categoryService.getAllActiveCategories();
        model.addAttribute("categoryList", categoryList);
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByEmail(auth.getName());


        //modelAndView.addObject("userName",   user.getName() + " " + user.getLastName());
        //modelAndView.addObject("userRole",   "Admin");

        if (categoryService.getCategoryByName(category.getCategoryName()) != null) {
            modelAndView.addObject("successMessage", "This category is already in the system");
            modelAndView.addObject("category", category);
        } else {
            categoryService.saveCategory(category);
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("successMessage", "Category added successfully");
        }
        modelAndView.setViewName("add-category");
        return "redirect:/admin/add-category";
    }


    @RequestMapping(value = "/admin/disable-category/{id}", method = RequestMethod.PUT)
    public String disableCategory(@PathVariable int id, @ModelAttribute("category") Category category) {

        Category cat = categoryService.getCategoryById(id);
        ModelAndView modelAndView = new ModelAndView();


        categoryService.setCategoryDisable(cat);
        modelAndView.setViewName("add-category");
        return "redirect:/admin/add-category";
    }


    @RequestMapping(value = "/admin/disable-subCategory/{id}", method = RequestMethod.PUT)
    public String disableSubCategory(@PathVariable int id, @ModelAttribute("subCategory") SubCategory subCategory) {

        SubCategory subCat = categoryService.getSubCategoryById(id);
        ModelAndView modelAndView = new ModelAndView();


        categoryService.setSubCategoryDisable(subCat);
        modelAndView.setViewName("sub-category");
        return "redirect:/admin/sub-category";
    }

    @RequestMapping(value = "/admin/sub-category", method = RequestMethod.GET) //to be called.
    public String getAllCategories(Model model, SubCategory subCategory) {

        ModelAndView modelAndView = new ModelAndView();

        // List<Category> categoryList = categoryService.getAllCategories();
        List<Category> categoryList = categoryService.getAllActiveCategories();
        model.addAttribute("categoryList", categoryList);
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("subCategory", new SubCategory());
        //modelAndView.addObject("userRole", "Admin");

        //Set<SubCategory> subCategoryList = categoryService.getAllSubCategoriesOfACategory(categoryID);

        List<SubCategory> subCategoryList = categoryService.getAllActiveSubCategories(categoryID);

        System.out.print("categoryID is =================================" + categoryID);


        List<SubCategory> list = new ArrayList<>(subCategoryList);


        model.addAttribute("subCategoryList", list);

        model.addAttribute("selectedCategoryID", categoryID);

        // modelAndView.setViewName("sub-category");
        return "sub-category";

    }


    @RequestMapping(value = "/admin/sub-category", method = RequestMethod.POST)
    public String createNewSubCategory(SubCategory subCategory, BindingResult bindingResult, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        List<Category> categoryList = categoryService.getAllActiveCategories();
        model.addAttribute("categoryList", categoryList);
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        //modelAndView.addObject("userRole", "Admin");

        //Set<SubCategory> subCategoryList = categoryService.getAllSubCategoriesOfACategory(categoryID);
        //List<SubCategory> list = new ArrayList<>(subCategoryList);

        List<SubCategory> subCategoryList = categoryService.getAllActiveSubCategories(categoryID);

        model.addAttribute("subCategoryList", subCategoryList);

        Category category = categoryService.getCategoryById(categoryID);

        System.out.println("WE ARE HERE ========================= " + subCategory.getSubCategoryName());

        if (categoryService.getSubCategoryByName(subCategory.getSubCategoryName()) != null) {
            modelAndView.addObject("successMessage", "This sub-category is already in the system");
            modelAndView.addObject("subCategory", subCategory);
        } else {
            categoryService.saveSubCategory(category, subCategory);
            modelAndView.addObject("subCategory", new SubCategory());
            modelAndView.addObject("successMessage", "SubCategory added successfully");
        }
        modelAndView.setViewName("sub-category");
        return "redirect:/admin/sub-category";


    }


    @RequestMapping(value = "/admin/select", method = RequestMethod.GET)
    public String allSubCategory(@ModelAttribute("categoryList") Category category, Model model,
                                 SubCategory subCategory, @RequestParam String option) {

        ModelAndView modelAndView = new ModelAndView();
        categoryID = Integer.parseInt(option);
        Set<SubCategory> subCategoryList = categoryService.getAllSubCategoriesOfACategory(categoryID);

        for (SubCategory s : subCategoryList) {
            System.out.println(s.getSubCategoryName());

        }
        List<SubCategory> list = new ArrayList<>(subCategoryList);


        model.addAttribute("subCategoryList", list);

        // modelAndView.addObject("category", new Category());

        // modelAndView.setViewName("sub-category");

        return "redirect:/admin/sub-category";


    }
}




