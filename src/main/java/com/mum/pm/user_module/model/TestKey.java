package com.mum.pm.user_module.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Chuang on 2017/4/26.
 */
@Entity
@Table(name = "test_key", schema = "onlinetest")
public class TestKey {

    private int testkeyId;

    private String testkeyValue;
    private int userid;
    private int studentid;

    private Date createdatetime;
    private Date expiredatetime;
    private int active;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "testkey_id")
    public int getTestkeyId() {
        return testkeyId;
    }

    public void setTestkeyId(int testkeyId) {
        this.testkeyId = testkeyId;
    }

    @Basic
    @Column(name = "testkey_value")
    public String getTestkeyValue() {
        return testkeyValue;
    }

    public void setTestkeyValue(String testkeyValue) {
        this.testkeyValue = testkeyValue;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "student_id")
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "createdatetime")
    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @Basic
    @Column(name = "expiredatetime")
    public Date getExpiredatetime() {
        return expiredatetime;
    }

    public void setExpiredatetime(Date expiredatetime) {
        this.expiredatetime = expiredatetime;
    }

    @Column(name = "active")
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestKey testKey = (TestKey) o;

        if (testkeyId != testKey.testkeyId) return false;
        if (active != testKey.active) return false;
        if (testkeyValue != null ? !testkeyValue.equals(testKey.testkeyValue) : testKey.testkeyValue != null)
            return false;
        if (createdatetime != null ? !createdatetime.equals(testKey.createdatetime) : testKey.createdatetime != null)
            return false;
        if (expiredatetime != null ? !expiredatetime.equals(testKey.expiredatetime) : testKey.expiredatetime != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = testkeyId;
        result = 31 * result + (testkeyValue != null ? testkeyValue.hashCode() : 0);
        result = 31 * result + (createdatetime != null ? createdatetime.hashCode() : 0);
        result = 31 * result + (expiredatetime != null ? expiredatetime.hashCode() : 0);
        result = 31 * result + active;
        return result;
    }
}
