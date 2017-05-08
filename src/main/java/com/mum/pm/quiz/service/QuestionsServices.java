package com.mum.pm.quiz.service;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.repository.QuestionRepository;
import com.mum.pm.quiz.model.CategorySubCategory;
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
    List<Question> questionsList;

    public List<QuestionSet> findBySubCategory(CategorySubCategory categorySubCategory) {

        System.out.println("Sub Category Size" +categorySubCategory.getSubCategories().size());
        questions = new ArrayList<QuestionSet>();
        for(int j=0;j<categorySubCategory.getSubCategories().size();j++){
            questionsList =new ArrayList<Question>();
            if( categorySubCategory.getSubCategories()!=null && !categorySubCategory.getSubCategories().isEmpty() ){
                System.out.println("Sub Category; "+categorySubCategory.getSubCategories().get(j).getSubCategoryId());
                questionsList= questionRepository.getAllBySubCategoryId(/*categorySubCategory.getSubCategories().get(j).getSubCategoryId()*/1);

                System.out.println("Questions List"+questionsList);
                Collections.shuffle(questionsList);
                    if(questionsList!=null) {
                        for (int i = 0; i </* questionsList.size()*/1; i++) {
                            options = new ArrayList<String>();
                            options.add(questionsList.get(i).getAnswer_1());
                            options.add(questionsList.get(i).getAnswer_2());
                            options.add(questionsList.get(i).getAnswer_3());
                            options.add(questionsList.get(i).getAnswer_4());
                            options.add(questionsList.get(i).getAnswer_5());
                            questions.add(new QuestionSet(questionsList.get(i).getQuestion_description(), questionsList.get(i).getId(), categorySubCategory.getSubCategories().get(j).getSubCategoryName(), options, questionsList.get(i).getCorrect_answer()));
                        }

                    }

           }
        }

        System.out.println("Questions1"+questions);

        return questions;

    }

    // Init some questions for testing
    @PostConstruct
    private void iniDataForTesting() {

        questions = new ArrayList<QuestionSet>();


    }

}
