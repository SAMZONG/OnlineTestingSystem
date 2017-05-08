package com.mum.pm.quiz.controller;

import javax.persistence.*;

/**
 * Created by binesh on 5/6/2017.
 */
@Entity
@Table
public class ExamQuestionDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int exam_detail_id;

    private int question_id;
    private int select_answer;
    private String subcategory_name;
    private int correct_answer;

    @ManyToOne
    @JoinColumn(name = "examReport_id")
    private ExamReport examReportDetails;

    public ExamQuestionDetails() {
    }

    public ExamQuestionDetails(int question_id, int select_answer, String subcategory_name, int correct_answer) {
        this.question_id = question_id;
        this.select_answer = select_answer;
        this.subcategory_name = subcategory_name;
        this.correct_answer = correct_answer;
    }

    public ExamReport getExamReportDetails() {
        return examReportDetails;
    }

    public void setExamReportDetails(ExamReport examReportDetails) {
        this.examReportDetails = examReportDetails;
    }

    public int getExam_detail_id() {
        return exam_detail_id;
    }

    public void setExam_detail_id(int exam_detail_id) {
        this.exam_detail_id = exam_detail_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSelect_answer() {
        return select_answer;
    }

    public void setSelect_answer(int select_answer) {
        this.select_answer = select_answer;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }
}
