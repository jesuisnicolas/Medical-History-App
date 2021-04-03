package com.example.medicalhistory;

public class Page {
    private String title;
    private String bodyText;
    private String fileRefrance;
    private String TAG = "Page";

    public Page(){

    }

    public void setBodyText(String text){bodyText = text;}
    public void setFileRefrance(String refrance){fileRefrance = refrance;}
    public String getBodyText(){return bodyText;}
    public String getTitle(){return title;}
}
