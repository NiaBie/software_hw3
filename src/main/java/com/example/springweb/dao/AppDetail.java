package com.example.springweb.dao;

import java.io.Serializable;

public class AppDetail implements Serializable {
    public int aid = -1;// TODO 唯一标识
    public String uid = null;
    public String appName = null;
    public int appKind = -1;
    public int dangerProbability = -1;
    public int dangerSerious = -1;
    public int controlClass = -1;

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppKind(int appKind) {
        this.appKind = appKind;
    }

    public void setDangerProbability(int dangerProbability) {
        this.dangerProbability = dangerProbability;
    }

    public void setDangerSerious(int dangerSerious) {
        this.dangerSerious = dangerSerious;
    }

    public void setControlClass(int controlClass) {
        this.controlClass = controlClass;
    }
}
