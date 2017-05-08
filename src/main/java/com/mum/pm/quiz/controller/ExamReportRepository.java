package com.mum.pm.quiz.controller;

import com.mum.pm.quiz.controller.ExamReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */

@Repository("examReportRepository")
public interface ExamReportRepository extends JpaRepository<ExamReport, Long> {


}
