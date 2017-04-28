package com.mum.pm.user_module.model;

/**
 * Created by larrywilson on 4/27/17.
 */

public class StudentAccessKeyForm {

    private String email;
    private String accessKey;

    public StudentAccessKeyForm() {
    }


    public StudentAccessKeyForm(String email, String accessKey) {
        this.email = email;
        this.accessKey = accessKey;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
