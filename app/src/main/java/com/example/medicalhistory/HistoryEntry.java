package com.example.medicalhistory;

import java.util.Date;

public class HistoryEntry {

    private String condition;
    private String doctor;
    private Date date;
    private String info;
    private String document; //I used a String type thinking about storing a link to the file.
    private String patient; //Here the idea is to know which patient owns this object

    // Constructor
    public HistoryEntry(String condition, String doctor, Date date, String info, String document, String patient) {
        this.condition = condition;
        this.doctor = doctor;
        this.date = date;
        this.info = info;
        this.document = document;
        this.patient = patient;
    }

    //Getters and Setters
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public String getPatient() {
        return patient;
    }
    public void setPatient(String patient) {
        this.patient = patient;
    }



}
