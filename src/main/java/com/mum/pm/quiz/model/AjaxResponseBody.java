package com.mum.pm.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AjaxResponseBody {


    @JsonProperty( "msg" )
    @XmlElement( required = true )
    String msg;
    @JsonProperty( "result" )
    @XmlElement( required = true )
    List<Questions> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Questions> getResult() {
        return result;
    }

    public void setResult(List<Questions> result) {
        this.result = result;
    }

}
