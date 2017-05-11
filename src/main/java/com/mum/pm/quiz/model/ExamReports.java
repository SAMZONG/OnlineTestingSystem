package com.mum.pm.quiz.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tareman on 5/2/2017.
 */

@Entity
@Table(name = "Report")
public class ExamReports {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private int report_id;
    @Column(name = "student_id")
    private int student_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "result")
    private double result;
    @Column(name = "category_name")
    private String category_name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examReports", orphanRemoval = true)
    @JsonIgnore
    private Set<SubReport> subReports;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "examReportsDetails", orphanRemoval = true)
    private Set<ExamQuestionDetails> examQuestionDetails;

    public ExamReports() {
    }

    public ExamReports(int student_id, int user_id, double result, String category_name) {
        this.student_id = student_id;
        this.user_id = user_id;
        this.result = result;
        this.category_name = category_name;
    }

    public Set<ExamQuestionDetails> getExamQuestionDetails() {
        return examQuestionDetails;
    }

    public void setExamQuestionDetails(Set<ExamQuestionDetails> examQuestionDetails) {
        this.examQuestionDetails = examQuestionDetails;
    }

    public Set<SubReport> getSubReports() {
        return subReports;
    }

    public void setSubReports(Set<SubReport> subReports) {
        this.subReports = subReports;
    }

    public void addSubReport(SubReport subReport) {
        subReports.add(subReport);
        subReport.setExamReports(this);
    }

    public void removeSubReport(SubReport subReport) {
        subReport.setExamReports(null);
        this.subReports.remove(subReport);
    }

    public void addSubReportDetails(ExamQuestionDetails examQuestionDetail) {
        examQuestionDetails.add(examQuestionDetail);
        examQuestionDetail.setExamReportsDetails(this);
    }

    public void removeSubReportDetails(ExamQuestionDetails examQuestionDetails) {
        examQuestionDetails.setExamReportsDetails(null);
        this.examQuestionDetails.remove(examQuestionDetails);
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
