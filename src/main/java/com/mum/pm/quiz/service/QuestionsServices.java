package com.mum.pm.quiz.service;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.repository.QuestionRepository;
import com.mum.pm.quiz.model.CategorySubCategory;
import com.mum.pm.quiz.model.QuestionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.test.maxquestion}")
    private int maxquestion;

    public int getMaxquestion() {
        return maxquestion;
    }

    public void setMaxquestion(int maxquestion) {
        this.maxquestion = maxquestion;
    }

    public List<QuestionSet> findBySubCategory(CategorySubCategory categorySubCategory) {

        questions = new ArrayList<QuestionSet>();
        for(int j=0;j<categorySubCategory.getSubCategories().size();j++){
            questionsList =new ArrayList<Question>();
            if( categorySubCategory.getSubCategories()!=null && !categorySubCategory.getSubCategories().isEmpty() ){
                questionsList= questionRepository.getAllBySubCategoryId(categorySubCategory.getSubCategories().get(j).getSubCategoryId());

                System.out.println("Questions List"+questionsList);
                Collections.shuffle(questionsList);


                    if(questionsList!=null) {
                        String subcategoryName= categorySubCategory.getSubCategories().get(j).getSubCategoryName();
                         if(questionsList.size()<20) {
                             addQuestion(questionsList,questionsList.size(),subcategoryName);
                         }
                         else {
                             addQuestion(questionsList,this.maxquestion,subcategoryName);


                         }
                    }

           }
        }

        System.out.println("Questions1"+questions);

        return questions;

    }

    private void addQuestion(List<Question> questionsList, int maxquestion,String subcategoryName) {
        System.out.println(maxquestion+" Question Selected");
        for (int i = 0; i < maxquestion; i++) {
            options = new ArrayList<String>();
            options.add(questionsList.get(i).getAnswer_1());
            options.add(questionsList.get(i).getAnswer_2());
            options.add(questionsList.get(i).getAnswer_3());
            options.add(questionsList.get(i).getAnswer_4());
            options.add(questionsList.get(i).getAnswer_5());
            questions.add(new QuestionSet(questionsList.get(i).getQuestion_description(), questionsList.get(i).getId(), subcategoryName, options, questionsList.get(i).getCorrect_answer()));
        }
    }

}
