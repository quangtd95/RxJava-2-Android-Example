package com.quangtd.rxexamples.loadMoreRecyclerView;

import java.io.Serializable;

/**
 * QuangTD on 11/4/2017.
 */

public class Student implements Serializable{
    private String name;
    private String emailId;

    public Student(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
