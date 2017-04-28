package com.mum.pm.question_module.model;

import javax.persistence.*;

/**
 * Created by phandungmykieu on 4/24/17.
 */



@Entity
@Table(name = "question_answer")
public class Question {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_answer_id")
    private int id;

    @Column(name = "question_description")
    private String question_description;

    @Column(name = "answer_1")
    private String answer_1;

    @Column(name = "answer_2")
    private String answer_2;

    @Column(name = "answer_3")
    private String answer_3;

    @Column(name = "answer_4")
    private String answer_4;

    @Column(name = "answer_5")
    private String answer_5;

    @Column(name = "correct_answer")
    private int correct_answer;

    @Column(name = "sub_category_id")
    private int sub_category_id;


    public  Question(){}

    public Question(String question_description, String answer_1, String answer_2, String answer_3, String answer_4, String answer_5, int correct_answer, int sub_category_id){
        this.id = id;
        this.question_description = question_description;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.answer_5 = answer_5;
        this.correct_answer = correct_answer;
        this.sub_category_id = sub_category_id;

    }


    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public void setAnswer_4(String answer_4) {
        this.answer_4 = answer_4;
    }

    public String getAnswer_5() {
        return answer_5;
    }

    public void setAnswer_5(String answer_5) {
        this.answer_5 = answer_5;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }


}
