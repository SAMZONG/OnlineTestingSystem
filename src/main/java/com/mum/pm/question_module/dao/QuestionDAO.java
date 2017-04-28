package com.mum.pm.question_module.dao;
import com.mum.pm.question_module.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by govin on 4/28/2017.
 */
@Repository("questionDAO")
public interface QuestionDAO extends CrudRepository<Question,Integer>{
        Iterable<Question> findBySubCategoryId(Integer subCategoryId);

}
