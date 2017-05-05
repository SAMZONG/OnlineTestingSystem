package com.mum.pm.quiz.service;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.repository.QuestionRepository;
import com.mum.pm.quiz.model.QuestionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionsServices {

    private List<QuestionSet> questions;
    List<String> options ;
    @Autowired
    QuestionRepository questionRepository;

    public List<QuestionSet> findBySubCategory(String username) {

        List<Question> questionsList = questionRepository.getAllBySubCategoryId(1);
        List<QuestionSet> result = new ArrayList<QuestionSet>();
        questions = new ArrayList<QuestionSet>();
        Collections.shuffle(questionsList);

        for(int i=0;i</*questionsList.size()*/3;i++){
         options= new ArrayList<String>();
         options.add(questionsList.get(i).getAnswer_1());
         options.add(questionsList.get(i).getAnswer_2());
         options.add(questionsList.get(i).getAnswer_3());
         options.add(questionsList.get(i).getAnswer_4());
         options.add(questionsList.get(i).getAnswer_5());
         questions.add(new QuestionSet(questionsList.get(i).getQuestion_description(),options,questionsList.get(i).getCorrect_answer()));
     }
        result= questions;


        return result;

    }

    // Init some questions for testing
    @PostConstruct
    private void iniDataForTesting() {

        questions = new ArrayList<QuestionSet>();
       /* List<String> op1 = new ArrayList<String>();
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

        questions = new ArrayList<QuestionSet>();

        QuestionSet questionSet1 = new QuestionSet("What is valid datatype", op1, 0);
        QuestionSet questionSet2 = new QuestionSet("What is valid class", op2,1);
        QuestionSet questionSet3 = new QuestionSet("What is valid function", op3, 2);

        questions.add(questionSet1);
        questions.add(questionSet2);
        questions.add(questionSet3);*/

    }

}
