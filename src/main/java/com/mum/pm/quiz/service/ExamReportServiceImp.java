package com.mum.pm.quiz.service;

import com.mum.pm.quiz.repository.ExamReportRepository;
import com.mum.pm.quiz.model.ExamReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tareman on 5/2/2017.
 */

@Service("examReportService")
public class ExamReportServiceImp implements ExamReportService {

    @Autowired
    ExamReportRepository examReportRepository;

    public ExamReportRepository getExamReportRepository() {
        return examReportRepository;
    }

    public void setExamReportRepository(ExamReportRepository examReportRepository) {
        this.examReportRepository = examReportRepository;
    }

    @Override
    public void save(ExamReports examReports) {
        examReportRepository.save(examReports);
    }

    @Override
    public List<ExamReports> getExamReports() {
        return examReportRepository.findAll();
    }


}
