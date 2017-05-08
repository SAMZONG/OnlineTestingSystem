package com.mum.pm.quiz.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.quiz.model.CategorySubCategory;
import com.mum.pm.quiz.model.QuestionSet;
import com.mum.pm.quiz.service.QuestionsServices;
import com.mum.pm.quiz.model.AjaxResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class QuizController {

    QuestionsServices questionsServices;

    public QuestionsServices getQuestionsServices() {
        return questionsServices;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;
    ExamReport examReport;
    Set<SubReport> subReports;
    Set<ExamQuestionDetails> examDeatils;
    ExamQuestionDetails examQuestionDetails;
    List<Category> categories;
    CategorySubCategory categorySubCategory;

    @Autowired
    ExamReportService examReportService;

    @Autowired
    public void setQuestionsServices(QuestionsServices questionsServices) {
        this.questionsServices = questionsServices;
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody CategorySubCategory categorySubCategory, Errors errors) {
        this.categorySubCategory = categorySubCategory;
        System.out.println("test: " + categorySubCategory);
        AjaxResponseBody result = new AjaxResponseBody();
        if (errors.hasErrors()) {

            return ResponseEntity.badRequest().body(result);

        }
        // category = search.getBkgsubcategory();
        List<QuestionSet> questions = questionsServices.findBySubCategory(categorySubCategory);
        result.setCategory(categorySubCategory.getCategory().getCategoryName());
        if (questions.isEmpty()) {
            result.setMsg("category not found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(questions);

        return ResponseEntity.ok(result);

    }

    @PostMapping("/saveResult")
    public void saveResult(@Valid @RequestBody AjaxResponseBody ajaxResponse) {
        double scoreByCategory = 0;
        double scoreBySubCategory = 0;
        for (int i = 0; i < ajaxResponse.getSelectedAnswer().size(); i++) {
            if (ajaxResponse.getSelectedAnswer().get(i) == ajaxResponse.getResult().get(i).getCorrectAnswer()) {
                scoreByCategory++;
            }
        }
        System.out.println("Selected Answwer " + ajaxResponse.getSelectedAnswer().toString());

        System.out.println(ajaxResponse.getMsg() + " Question :" + ajaxResponse.getResult().toString());

        examReport = new ExamReport(5, 1, scoreByCategory, ajaxResponse.getCategory());

        subReports = new HashSet<SubReport>();
        SubReport subReport = new SubReport();
        for (int i = 0; i < categorySubCategory.getSubCategories().size(); i++) {
            for (int j = 0; j < ajaxResponse.getSelectedAnswer().size(); j++) {
                if (categorySubCategory.getSubCategories().get(i).getSubCategoryName().equals(ajaxResponse.getResult().get(j).getSubCategoryName())) {
                    if (ajaxResponse.getSelectedAnswer().get(j) == ajaxResponse.getResult().get(j).getCorrectAnswer()) {
                        scoreBySubCategory++;
                    }
                }
            }

            subReport = new SubReport(categorySubCategory.getSubCategories().get(i).getSubCategoryName(), scoreBySubCategory);

            subReport.setExamReport(examReport);
            subReports.add(subReport);

        }


        System.out.println("Test " + subReport.toString());
        examReport.setSubReports(subReports);


        System.out.println("Test1 " + scoreByCategory);
        System.out.println("Test2 " + scoreBySubCategory);


        examDeatils = new HashSet<ExamQuestionDetails>();
        ExamQuestionDetails examQuestionDetail = new ExamQuestionDetails();

        for (int i = 0; i < ajaxResponse.getResult().size(); i++) {

            examQuestionDetail = new ExamQuestionDetails(ajaxResponse.getResult().get(i).getQuestionID(), ajaxResponse.getSelectedAnswer().get(i), ajaxResponse.getResult().get(i).getSubCategoryName(), ajaxResponse.getResult().get(i).getCorrectAnswer());
            examQuestionDetail.setExamReportDetails(examReport);
            examDeatils.add(examQuestionDetail);
        }
        examReport.setExamQuestionDetails(examDeatils);
        // examReport.addSubReportDetails(examQuestionDetail);
        System.out.println(examReport.toString());

        try {
            examReportService.save(examReport);
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println("Report saved");
    }


}
