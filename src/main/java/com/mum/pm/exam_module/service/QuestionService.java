package com.mum.pm.exam_module.service;
import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created by govin on 4/27/2017.
 */


public interface QuestionService {
    public Iterable<Question> getAllQuestions();

   public Iterable<Question> getQuestionsBySubCategoryID(Integer subCategoryId);
}
