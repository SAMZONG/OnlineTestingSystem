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
    List<String> options;
    @Autowired
    QuestionRepository questionRepository;
    List<Question> questionsList;

    public String getMaxquestion() {
        return maxquestion;
    }

    public void setMaxquestion(String maxquestion) {
        this.maxquestion = maxquestion;
    }

    @Value("${spring.test.maxquestion}")
    private String maxquestion;


    public List<QuestionSet> findBySubCategory(CategorySubCategory categorySubCategory) {
        System.out.println("Size " + categorySubCategory.getSubCategories().size());
        questions = new ArrayList<QuestionSet>();
        for (int j = 0; j < categorySubCategory.getSubCategories().size(); j++) {
            try {

                if (categorySubCategory.getSubCategories() != null && !categorySubCategory.getSubCategories().isEmpty()) {
                    questionsList = new ArrayList<Question>();
                    try {
                        questionsList = questionRepository.getAllBySubCategoryId(categorySubCategory.getSubCategories().get(j).getSubCategoryId());

                        Collections.shuffle(questionsList);
                        if (questionsList != null && !questionsList.isEmpty()) {

                            String subcategoryName = categorySubCategory.getSubCategories().get(j).getSubCategoryName();
                            if (questionsList.size() < 20) {
                                addQuestion(questionsList, questionsList.size(), subcategoryName);
                            } else {
                                addQuestion(questionsList, Integer.parseInt(this.maxquestion.trim().toString()), subcategoryName);


                            }
                        } else {
                        }
                    } catch (NumberFormatException e1) {
                        System.out.println("Error occurred " + e1);

                    }

                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error occurred " + e);
            }
        }

        return questions;

    }

    private void addQuestion(List<Question> questionsList, int maxquestion, String subcategoryName) {

        for (int i = 0; i < 3; i++) {
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
