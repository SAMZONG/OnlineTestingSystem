package com.mum.pm.report_module.model;

import javax.persistence.*;

/**
 * Created by tareman on 5/2/2017.
 */

@Entity
@Table(name = "report")
public class ExamReport {


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

    public ExamReport() {
    }

    public ExamReport(int student_id, int user_id, double result, String category_name) {
        this.student_id = student_id;
        this.user_id = user_id;
        this.result = result;
        this.category_name = category_name;
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
