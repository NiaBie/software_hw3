package com.example.springweb.dao;

import java.io.Serializable;

public class UserDetail implements Serializable {
    private String uid;
    private String userName;
    private String password;

    public UserDetail() {
        uid = null;
        userName = null;
        password = null;
    }
    
    public UserDetail(String uid, String userName, String password) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return uid + "," + userName + "," + password;
    }
}
