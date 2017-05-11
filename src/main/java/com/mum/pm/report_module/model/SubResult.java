package com.mum.pm.report_module.model;

import javax.persistence.*;

/**
 * Created by tareman on 5/8/2017.
 */

@Entity
@Table(name = "sub_result")
public class SubResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sub_result_id")
    private int id;

    @Column(name = "result_id")
    private int result_id;

    @Column(name = "sub_category_name")
    private String sub_category_name;

    @Column(name = "score")
    private double score;

    public SubResult() {
    }

    public SubResult(int result_id, String sub_category_name, double score) {
        this.result_id = result_id;
        this.sub_category_name = sub_category_name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
