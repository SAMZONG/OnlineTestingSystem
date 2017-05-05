package com.mum.pm.quiz.service;

import com.mum.pm.quiz.model.Questions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServices {

    private List<Questions> questions;

    // Love Java 8
   /* public List<Questions> findByUserNameOrEmail(String username) {

        List<Questions> result = questions.stream().filter(x -> x.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());

        return result;

    }*/
    public List<Questions> findByUserNameOrEmail(String username) {

       List<Questions> result = new ArrayList<Questions>();
        result= questions;


        return result;

    }

    // Init some questions for testing
    @PostConstruct
    private void iniDataForTesting() {
        List<String> op1 = new ArrayList<String>();
        List<String> op2 = new ArrayList<String>();
        List<String> op3 = new ArrayList<String>();
        op1.add("int");
        op1.add("jpt");
        op1.add("tt");
        op1.add("op");
        op1.add("int");

        op2.add("fin class(){}");
        op2.add("bkg {}");
        op2.add("{} abc");
        op2.add("{abc}");
        op2.add("jpt");

        op3.add("int");
        op3.add("()abc()");
        op3.add("abc()");
        op3.add("()abc");
        op3.add("(abc)");

        questions = new ArrayList<Questions>();

        Questions questions1 = new Questions("What is valid datatype", op1, 0);
        Questions questions2 = new Questions("What is valid class", op2,1);
        Questions questions3 = new Questions("What is valid function", op3, 2);

        questions.add(questions1);
        questions.add(questions2);
        questions.add(questions3);

    }

}
