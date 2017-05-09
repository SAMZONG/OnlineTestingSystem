package com.mum.pm.quiz.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.quiz.model.CategorySubCategory;
import com.mum.pm.quiz.model.QuestionSet;
import com.mum.pm.quiz.service.QuestionsServices;
import com.mum.pm.quiz.model.AjaxResponseBody;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.repository.TestKeyRepository;
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
    String subcategory="";
    ExamReport examReport;
    Set<SubReport> subReports;
    Set<ExamQuestionDetails> examDeatils;
    ExamQuestionDetails examQuestionDetails;
    List<Category> categories;
    CategorySubCategory categorySubCategory;
    String accessKey;

    @Autowired
    private TestKeyRepository testKeyRepository;

    @Autowired
    ExamReportService examReportService;

    @Autowired
    public void setQuestionsServices(QuestionsServices questionsServices) {
        this.questionsServices = questionsServices;
    }

    @PostMapping("/student/getQuestions")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody CategorySubCategory categorySubCategory, Errors errors) {
        this.subcategory="";
        this.categorySubCategory = categorySubCategory;
        accessKey=categorySubCategory.getAccessKey();
        AjaxResponseBody result = new AjaxResponseBody();
        if (errors.hasErrors()) {

            return ResponseEntity.badRequest().body(result);

        }
        List<QuestionSet> questions = questionsServices.findBySubCategory(categorySubCategory);

        for (int i = 0; i < categorySubCategory.getSubCategories().size(); i++) {
            if(i==categorySubCategory.getSubCategories().size()-1){
                subcategory += categorySubCategory.getSubCategories().get(i).getSubCategoryName();
            }else {
                subcategory +=  categorySubCategory.getSubCategories().get(i).getSubCategoryName()+", " ;
            }
        }
        result.setCategory(subcategory);
        if (questions.isEmpty()) {
            result.setMsg("category not found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(questions);

        return ResponseEntity.ok(result);

    }

    @PostMapping("/student/saveResult")
    public void saveResult(@Valid @RequestBody AjaxResponseBody ajaxResponse) {
       try {
           TestKey testKey = testKeyRepository.findByTestkeyValue(accessKey);
            System.out.println(testKey.getStudentid());
           double scoreByCategory = 0;
           double scoreBySubCategory = 0;
           for (int i = 0; i < ajaxResponse.getSelectedAnswer().size(); i++) {
               if (ajaxResponse.getSelectedAnswer().get(i)+1 == ajaxResponse.getResult().get(i).getCorrectAnswer()) {
                   scoreByCategory++;
               }
           }

           examReport = new ExamReport(testKey.getStudentid(), testKey.getUserid(), scoreByCategory, this.categorySubCategory.getCategory().getCategoryName());

           subReports = new HashSet<SubReport>();
           SubReport subReport = new SubReport();
           for (int i = 0; i < categorySubCategory.getSubCategories().size(); i++) {
               scoreBySubCategory = 0;
               for (int j = 0; j < ajaxResponse.getSelectedAnswer().size(); j++) {
                   if (categorySubCategory.getSubCategories().get(i).getSubCategoryName().equals(ajaxResponse.getResult().get(j).getSubCategoryName())) {
                       if (ajaxResponse.getSelectedAnswer().get(j)+1 == ajaxResponse.getResult().get(j).getCorrectAnswer()) {
                           scoreBySubCategory++;
                       }
                   }
               }

               subReport = new SubReport(categorySubCategory.getSubCategories().get(i).getSubCategoryName(), scoreBySubCategory);

               subReport.setExamReport(examReport);
               subReports.add(subReport);

           }

           examReport.setSubReports(subReports);
           examDeatils = new HashSet<ExamQuestionDetails>();
           ExamQuestionDetails examQuestionDetail = new ExamQuestionDetails();

           for (int i = 0; i < ajaxResponse.getResult().size(); i++) {

               examQuestionDetail = new ExamQuestionDetails(ajaxResponse.getResult().get(i).getQuestionID(), ajaxResponse.getSelectedAnswer().get(i)+1, ajaxResponse.getResult().get(i).getSubCategoryName(), ajaxResponse.getResult().get(i).getCorrectAnswer());
               examQuestionDetail.setExamReportDetails(examReport);
               examDeatils.add(examQuestionDetail);
           }
           examReport.setExamQuestionDetails(examDeatils);

           try {
               examReportService.save(examReport);
           } catch (Exception e) {
               e.getStackTrace();
           }
       }catch (Exception e){
           System.out.println("Error Occurred "+e.getStackTrace());
       }

    }

}
