package com.example.wikipill;

import android.app.Application;

public class global extends Application {
    String newMedName;
    String newMedDose;
    String newMedTime;
    int visi ;
    int medlog;
    int language;

    public global() {
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
}
