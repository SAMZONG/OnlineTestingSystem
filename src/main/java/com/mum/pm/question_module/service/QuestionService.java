package com.mum.pm.question_module.service;

import com.mum.pm.question_module.model.Question;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by phandungmykieu on 4/26/17.
 */
public interface QuestionService {

    void uploadQuestions(Path path);

    void save(Question question);

    Question findById(int id);

    List<Question> getAllQuestions();

    Iterable<Question> getQuestionsBySubCategoryID(Integer subCategoryId);

}
