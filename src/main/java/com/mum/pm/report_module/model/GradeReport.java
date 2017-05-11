package com.mum.pm.report_module.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tareman on 5/4/2017.
 */
@Entity
@Table(name = "reportview")
public class GradeReport {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_number")
    private int id;

    @Column(name = "student_id")
    private int student_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "assigner")
    private String assigner;
    @Column(name = "category_name")
    private String category_name;
    @Column(name = "result")
    private double result;
    @Column(name = "percentile")
    private double percentile;
    @Column(name = "grade")
    private String grade;

    public GradeReport() {

    }


    public GradeReport(int student_id, String first_name, String last_name, String email, String assigner, String category_name, double result, double percentile, String grade) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.assigner = assigner;
        this.category_name = category_name;
        this.result = result;
        this.percentile = percentile;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getPercentile() {
        return percentile;
    }

    public void setPercentile(double percentile) {
        this.percentile = percentile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
