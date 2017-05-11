package com.mum.pm.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class QuestionSet {


    @JsonProperty("question")
    @XmlElement(required = true)
    String question;
    int questionID;
    String subCategoryName = "";
    @JsonProperty("choices")
    @XmlElement(required = true)
    List<String> choices = new ArrayList<String>();

    @JsonProperty("correctAnswer")
    @XmlElement(required = true)
    int correctAnswer;

    public QuestionSet(String question, int questionID, String subCategoryName, List<String> choices, int correctAnswer) {
        this.question = question;
        this.questionID = questionID;
        this.subCategoryName = subCategoryName;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public QuestionSet() {
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "QuestionSet{" +
                "question='" + question + '\'' +
                ", questionID=" + questionID +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", choices=" + choices +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
