package com.nerazzurro.wh.ui.home;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class WHLogInput {

    private String startam;
    private String startpm;
    private String stopam;
    private String stoppm;
    private String workedhours;

    public WHLogInput() {
    }

    public WHLogInput(String startam, String stopam, String startpm, String stoppm, String workedhours) {
        this.startam = startam;
        this.startpm = startpm;
        this.stopam = stopam;
        this.stoppm = stoppm;
        this.workedhours = workedhours;
    }

    public String getStartam() {
        return startam;
    }

    public void setStartam(String startam) {
        this.startam = startam;
    }

    public String getStartpm() {
        return startpm;
    }

    public void setStartpm(String startpm) {
        this.startpm = startpm;
    }

    public String getStopam() {
        return stopam;
    }

    public void setStopam(String stopam) {
        this.stopam = stopam;
    }

    public String getStoppm() {
        return stoppm;
    }

    public void setStoppm(String stoppm) {
        this.stoppm = stoppm;
    }

    public String getWorkedhours() {
        return workedhours;
    }

    public void setWorkedhours(String workedhours) {
        this.workedhours = workedhours;
    }
}
