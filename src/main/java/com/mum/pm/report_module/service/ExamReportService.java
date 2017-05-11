package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.ExamReport;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */
public interface ExamReportService {

    void save(ExamReport examReport);

    ExamReport findById(int id);

    List<ExamReport> getAllExamReports();
}
