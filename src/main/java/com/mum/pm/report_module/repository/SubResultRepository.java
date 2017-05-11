package com.mum.pm.report_module.repository;

import com.mum.pm.report_module.model.SubResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tareman on 5/8/2017.
 */

@Repository("subResultRepository")
public interface SubResultRepository extends JpaRepository<SubResult, Long> {

    // void save(SubResult subResult);

    SubResult findById(int id);

    @Query("SELECT b FROM SubResult b")
    List<SubResult> getAllSubResults();

    // @Query("SELECT b FROM SubResult b")
    // List<SubResult> getAllSubResultByReportId(int reportId);

}
