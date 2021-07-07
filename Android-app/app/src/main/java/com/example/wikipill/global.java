package com.example.wikipill;

import android.app.Application;

public class global extends Application {
    String newMedName;
    String newMedDose;
    String newMedTime;
    String fex;
    String para;
    String sari;
    int visi ;
    int medlog;
    int language = 1;
    int notification;
    int variable;

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getSari() {
        return sari;
    }

    public void setSari(String sari) {
        this.sari = sari;
    }

    public global() {
    }

    public String getFex() {
        return fex;
    }

    public void setFex(String fex) {
        this.fex = fex;
    }

    public int getMedlog() {
        return medlog;
    }

    public void setMedlog(int medlog) {
        this.medlog = medlog;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getVisi() {
        return visi;
    }

    public void setVisi(int visi) {
        this.visi = visi;
    }

    public String getNewMedName() {
        return newMedName;
    }

    public void setNewMedName(String newMedName) {
        this.newMedName = newMedName;
    }

    public String getNewMedDose() {
        return newMedDose;
    }

    public void setNewMedDose(String newMedDose) {
        this.newMedDose = newMedDose;
    }

    public String getNewMedTime() {
        return newMedTime;
    }

    public void setNewMedTime(String newMedTime) {
        this.newMedTime = newMedTime;
    }
    
    public int getVariable() {
        return var;
    }

    public void setVariable(int variable) {
        this.var = variable;
    }
    
}
