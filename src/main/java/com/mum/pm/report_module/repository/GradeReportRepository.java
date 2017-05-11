package com.mum.pm.report_module.repository;

import com.mum.pm.report_module.model.GradeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tareman on 5/4/2017.
 */
@Repository("gradeReportRepository")
public interface GradeReportRepository extends JpaRepository<GradeReport, Long> {

    //  void save(GradeReport report);
    GradeReport findById(int id);

    @Query("SELECT b FROM GradeReport b")
    List<GradeReport> getAllReports();
}
