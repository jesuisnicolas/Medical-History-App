package com.example.medicalhistory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.SortedMap;
import java.util.TreeMap;

public class Page {
    private String bodyText;
    private String TAG = "Page";
//    private String fileRefrance;
    private String condition;
    private String doctor;
    private String date;
    private String extraInfo;
//    private String filePath;

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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
//    public String getFilePath() {
//        return filePath;
//    }
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }


    public String pageToJson(){

        //create a map to store the values
        SortedMap<String, String> entry = new TreeMap<String, String>();
        entry.put("condition", condition);
        entry.put("doctor", doctor);
        entry.put("date", date);
        entry.put("extraInfo", extraInfo);
//        entry.put("filePath", filePath);

//      convert the map to Gson to store.
        Gson gsonObj = new Gson();
        Type gsonTypeObj = new TypeToken<String>(){}.getType();

        String jsonString = gsonObj.toJson(entry, gsonTypeObj);
        System.out.println(jsonString);

        return jsonString;
    }





//    public Page(){
//    }
//    public void setBodyText(String bodyText){this.bodyText = bodyText;}
//    public void setFileRefrance(String fileRefrence){this.fileRefrance = fileRefrence;}
//
//    public String getFileRefrance(){return bodyText;}
//    public String getBodyText(){return bodyText;}
}
