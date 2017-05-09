package com.mum.pm.quiz.model;


import javax.persistence.*;

/**
 * Created by binesh on 5/6/2017.
 */
@Entity
@Table
public class SubReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sub_report_Id;

    private String subcategory_name;
    private double sub_score;
    @ManyToOne
    @JoinColumn(name = "examReport_id")
    private ExamReports examReports;

    public ExamReports getExamReports() {
        return examReports;
    }

    public void setExamReports(ExamReports examReports) {
        this.examReports = examReports;
    }

    public SubReport() {
    }

    public SubReport(String subcategory_name, double sub_score) {

        this.subcategory_name = subcategory_name;
        this.sub_score = sub_score;
    }

    public int getSub_report_Id() {
        return sub_report_Id;
    }

    public void setSub_report_Id(int sub_report_Id) {
        this.sub_report_Id = sub_report_Id;
    }


    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public double getSub_score() {
        return sub_score;
    }

    public void setSub_score(double sub_score) {
        this.sub_score = sub_score;
    }

    @Override
    public String toString() {
        return "SubReport{" +
                "sub_report_Id=" + sub_report_Id +
                ", subcategory_name='" + subcategory_name + '\'' +
                ", sub_score=" + sub_score +
                ", examReports=" + examReports +
                '}';
    }
}
