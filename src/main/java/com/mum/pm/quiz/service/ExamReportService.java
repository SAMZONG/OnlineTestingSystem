package com.mum.pm.quiz.service;

import com.mum.pm.quiz.model.ExamReports;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */
public interface ExamReportService {

    void save(ExamReports examReports);
    List<ExamReports> getExamReports();
}
