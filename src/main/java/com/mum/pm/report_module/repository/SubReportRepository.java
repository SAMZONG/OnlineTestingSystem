package com.mum.pm.report_module.repository;

/**
 * Created by tareman on 5/10/2017.
 */

import com.mum.pm.quiz.model.SubReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("subReportRepository")
public interface SubReportRepository extends JpaRepository<SubReport, Long> {

    // void save(SubResult subResult);

    @Query("SELECT b FROM SubReport b")
    List<SubReport> getAllSubReports();

    @Query("Select b FROM SubReport b  WHERE b.examReports = :reportId")
    List<SubReport> getReportsById(@Param("reportId") int reportId);

}
