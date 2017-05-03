package com.mum.pm.question_module.repository;

import com.mum.pm.question_module.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(int id);
    Iterable<Question> findBySubCategoryId(Integer subCategoryId);

    List<Question> findAll();
}