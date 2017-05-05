package com.mum.pm.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Questions {


    @JsonProperty( "question" )
    @XmlElement( required = true )
    String question;

    @JsonProperty( "choices" )
    @XmlElement( required = true )
    List<String> choices  =new ArrayList<String>();

    @JsonProperty( "correctAnswer" )
    @XmlElement( required = true )
    int correctAnswer;

    public Questions(String question, List<String> choices, int correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public Questions() {
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", choices=" + choices +
                ", correctAnswer='" + correctAnswer + '\'' +
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
