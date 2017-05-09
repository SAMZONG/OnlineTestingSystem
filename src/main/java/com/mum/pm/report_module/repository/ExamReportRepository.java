package com.mum.pm.report_module.repository;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.report_module.model.ExamReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */

@Repository("reportRepository")
public interface ExamReportRepository extends JpaRepository<Question, Long> {

    void save(ExamReport examReport);

    ExamReport findById(int id);

    @Query("SELECT b FROM ExamReport b")
    List<ExamReport> getAllExamReports();

}
