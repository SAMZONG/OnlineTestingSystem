package com.mum.pm.exam_module.service;

import com.mum.pm.question_module.dao.QuestionDAO;
import com.mum.pm.question_module.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by govin on 4/27/2017.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    @Override
    public Iterable<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    @Override
    public Iterable<Question> getQuestionsBySubCategoryID(Integer subCategoryId) {
        return questionDAO.findBySubCategoryId(subCategoryId);
    }
}
