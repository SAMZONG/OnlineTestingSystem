package com.mum.pm.quiz.controller;

import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.quiz.model.*;
import com.mum.pm.quiz.service.ExamReportService;
import com.mum.pm.quiz.service.QuestionsServices;
import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.repository.TestKeyRepository;
import com.mum.pm.user_module.service.StudentService;
import com.mum.pm.user_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class QuizController {

    @Autowired
    QuestionsServices questionsServices;

    @Autowired
    StudentService studentService;
    String subcategory = "";
    ExamReports examReports;
    Set<SubReport> subReports;
    Set<ExamQuestionDetails> examDeatils;
    ExamQuestionDetails examQuestionDetails;
    List<Category> categories;
    CategorySubCategory categorySubCategory;
    String accessKey;
    @Autowired
    ExamReportService examReportService;
    @Autowired
    UserService userService;
    @Autowired
    private TestKeyRepository testKeyRepository;

    public QuestionsServices getQuestionsServices() {
        return questionsServices;
    }

    @Autowired
    public void setQuestionsServices(QuestionsServices questionsServices) {
        this.questionsServices = questionsServices;
    }

    @PostMapping("/student/getQuestions")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody CategorySubCategory categorySubCategory, Errors errors) {
        this.subcategory = "";
        this.categorySubCategory = categorySubCategory;
        accessKey = categorySubCategory.getAccessKey();
        AjaxResponseBody result = new AjaxResponseBody();
        if (errors.hasErrors()) {

            return ResponseEntity.badRequest().body(result);

        }
        List<QuestionSet> questions = questionsServices.findBySubCategory(categorySubCategory);

        for (int i = 0; i < categorySubCategory.getSubCategories().size(); i++) {
            if (i == categorySubCategory.getSubCategories().size() - 1) {
                subcategory += categorySubCategory.getSubCategories().get(i).getSubCategoryName();
            } else {
                subcategory += categorySubCategory.getSubCategories().get(i).getSubCategoryName() + ", ";
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
                if (ajaxResponse.getSelectedAnswer().get(i) + 1 == ajaxResponse.getResult().get(i).getCorrectAnswer()) {
                    scoreByCategory++;
                }
            }
            scoreByCategory = (int) Math.round((scoreByCategory / ajaxResponse.getResult().size() * 100) * 100) / (double) 100;
            examReports = new ExamReports(testKey.getStudentid(), testKey.getUserid(), scoreByCategory, this.categorySubCategory.getCategory().getCategoryName());

            subReports = new HashSet<SubReport>();
            SubReport subReport = new SubReport();
            for (int i = 0; i < categorySubCategory.getSubCategories().size(); i++) {
                scoreBySubCategory = 0;
                for (int j = 0; j < ajaxResponse.getSelectedAnswer().size(); j++) {
                    if (categorySubCategory.getSubCategories().get(i).getSubCategoryName().equals(ajaxResponse.getResult().get(j).getSubCategoryName())) {
                        if (ajaxResponse.getSelectedAnswer().get(j) + 1 == ajaxResponse.getResult().get(j).getCorrectAnswer()) {
                            scoreBySubCategory++;
                        }
                    }
                }

                scoreBySubCategory = (int) Math.round((scoreBySubCategory / categorySubCategory.getSubCategories().size() * 100) * 100) / (double) 100;
                subReport = new SubReport(categorySubCategory.getSubCategories().get(i).getSubCategoryName(), scoreBySubCategory);

                subReport.setExamReports(examReports);
                subReports.add(subReport);

            }

            examReports.setSubReports(subReports);
            examDeatils = new HashSet<ExamQuestionDetails>();
            ExamQuestionDetails examQuestionDetail = new ExamQuestionDetails();

            for (int i = 0; i < ajaxResponse.getResult().size(); i++) {

                examQuestionDetail = new ExamQuestionDetails(ajaxResponse.getResult().get(i).getQuestionID(), ajaxResponse.getSelectedAnswer().get(i) + 1, ajaxResponse.getResult().get(i).getSubCategoryName(), ajaxResponse.getResult().get(i).getCorrectAnswer());
                examQuestionDetail.setExamReportsDetails(examReports);
                examDeatils.add(examQuestionDetail);
            }
            examReports.setExamQuestionDetails(examDeatils);

            try {
                examReportService.save(examReports);
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error Occurred " + e.getStackTrace());
        }

    }

    @RequestMapping(value = "/student/reports", method = RequestMethod.GET)
    public List<Report> getAllReports() {
        List<ExamReports> examReports = examReportService.getExamReports();
        List<Report> reports = new ArrayList<Report>();
        for (ExamReports e : examReports) {
            try {
                int reportId = e.getReport_id();
                Student student = studentService.findByStudentId(e.getStudent_id());
                System.out.println(student);
                String studentName = student.getFirstName() + " " + student.getLastName();
                User user = userService.findUserById(e.getUser_id());
                String coachName = user.getName() + " " + user.getLastName();
                String category = e.getCategory_name();
                double score = e.getResult();
                Report report = new Report(reportId, studentName, coachName, category, score);
                reports.add(report);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return reports;
    }


}
