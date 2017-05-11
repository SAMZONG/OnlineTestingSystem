package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.GradeReport;

import java.util.List;

/**
 * Created by tareman on 5/4/2017.
 */
public interface GradeReportService {

    void save(GradeReport report);

    GradeReport findById(int id);

    List<GradeReport> getAllReports();

}
