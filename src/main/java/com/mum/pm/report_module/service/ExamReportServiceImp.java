package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.ExamReport;
import com.mum.pm.report_module.repository.ExamReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */

@Service("reportService")
public class ExamReportServiceImp implements ExamReportService {

    @Autowired
    ExamReportRepository examReportRepository;


    @Override
    public void save(ExamReport examReport) {
        examReportRepository.save(examReport);
    }

    @Override
    public ExamReport findById(int id) {
        return examReportRepository.findById(id);
    }

    @Override
    public List<ExamReport> getAllExamReports() {
        return examReportRepository.getAllExamReports();
    }
}
