package com.mum.pm.report_module.repository;

import com.mum.pm.report_module.model.ExamReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */

@Repository("reportRepository")
public interface ExamReportRepository extends JpaRepository<ExamReport, Long> {

    // void save(ExamReport examReport);

    @Query("SELECT b FROM ExamReport b")
    List<ExamReport> getAllExamReports();

    ExamReport findById(int id);


}
