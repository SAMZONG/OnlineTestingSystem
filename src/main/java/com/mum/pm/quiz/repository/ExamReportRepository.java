package com.mum.pm.quiz.repository;

import com.mum.pm.quiz.model.ExamReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tareman on 5/2/2017.
 */

@Repository("examReportRepository")
public interface ExamReportRepository extends JpaRepository<ExamReports, Long> {


}
