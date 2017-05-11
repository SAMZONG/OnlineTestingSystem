package com.mum.pm.report_module.service;

import com.mum.pm.quiz.model.SubReport;

import java.util.List;

/**
 * Created by tareman on 5/10/2017.
 */
public interface SubReportService {

    List<SubReport> getAllSubReports();

    List<SubReport> getReportsById(int reportId);
}
