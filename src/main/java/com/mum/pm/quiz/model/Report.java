package com.mum.pm.quiz.model;

/**
 * Created by manzil on 5/9/2017.
 */
public class Report {

    private int reportId;

    public Report(int reportId, String studentName, String coachName, String categoryName, double score) {
        this.reportId = reportId;
        this.studentName = studentName;
        this.coachName = coachName;
        this.categoryName = categoryName;
        this.score = score;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private String studentName;
    private String coachName;
    private String categoryName;
    private double score;

}
