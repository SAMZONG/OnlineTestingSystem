package com.mum.pm.report_module.service;

import com.mum.pm.quiz.model.SubReport;
import com.mum.pm.report_module.repository.SubReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tareman on 5/10/2017.
 */
@Service("subReportService")
public class SubReportServiceImp implements SubReportService {

    @Autowired
    SubReportRepository subReportRepository;

    @Override
    public List<SubReport> getAllSubReports() {
        return subReportRepository.getAllSubReports();
    }

    @Override
    public List<SubReport> getReportsById(int reportId) {
        return subReportRepository.getReportsById(reportId);
    }
}
