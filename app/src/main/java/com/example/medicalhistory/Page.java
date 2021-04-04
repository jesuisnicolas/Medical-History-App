package com.example.medicalhistory;

import java.util.Date;

public class Page {
    private String bodyText;
    private String TAG = "Page";
    private String fileRefrance;
    public Page(){
    }
    public void setBodyText(String bodyText){this.bodyText = bodyText;}
    public void setFileRefrance(String fileRefrence){this.fileRefrance = fileRefrence;}

    public String getFileRefrance(){return bodyText;}
    public String getBodyText(){return bodyText;}
}
