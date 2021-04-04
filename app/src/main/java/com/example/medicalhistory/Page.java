package com.example.medicalhistory;

import java.util.Date;

public class Page {
    private String condition;
    private String doctor;
    private String date;
    private String extraInfo;
    private String TAG = "Page";

    public Page(){
    }


    public String getCondition() {return condition;}

    public void setCondition(String condition) {this.condition = condition;}

    public String getDoctor() {return doctor;}

    public void setDoctor(String doctor) {this.doctor = doctor;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getExtraInfo() {return extraInfo;}

    public void setExtraInfo(String extraInfo) {this.extraInfo = extraInfo;}
}
