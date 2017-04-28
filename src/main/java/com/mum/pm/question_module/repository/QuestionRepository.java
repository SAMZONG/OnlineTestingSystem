package com.mum.pm.question_module.repository;

import com.mum.pm.question_module.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {

}