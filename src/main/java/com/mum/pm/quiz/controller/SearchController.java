package com.mum.pm.quiz.controller;

import com.mum.pm.quiz.service.QuestionsServices;
import com.mum.pm.quiz.model.SearchCriteria;
import com.mum.pm.quiz.model.AjaxResponseBody;
import com.mum.pm.quiz.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SearchController {

    QuestionsServices questionsServices;

    @Autowired
    public void setUserService(QuestionsServices questionsServices) {
        this.questionsServices = questionsServices;
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
       // System.out.println("Sah");
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

           /// result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<Questions> questions = questionsServices.findByUserNameOrEmail(search.getSubcategory());
        if (questions.isEmpty()) {
            result.setMsg("category not found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(questions);

        return ResponseEntity.ok(result);

    }
    @PostMapping("/saveResult")
    public void saveResult (@Valid @RequestBody AjaxResponseBody question) {
        System.out.println("Saving ressult");
      System.out.println(question.getMsg()+"Question :"+question.getResult().toString());


    }

}
