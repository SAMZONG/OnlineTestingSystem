package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.GradeReport;
import com.mum.pm.report_module.repository.GradeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tareman on 5/4/2017.
 */

@Service("gradeReportService")
public class GradeReportServiceImp implements GradeReportService {

    @Autowired
    GradeReportRepository gradeReportRepository;


    @Override
    public void save(GradeReport report) {
        gradeReportRepository.save(report);
    }

    @Override
    public GradeReport findById(int id) {
        return gradeReportRepository.findById(id);
    }

    @Override
    public List<GradeReport> getAllReports() {
        return gradeReportRepository.getAllReports();
    }
}
