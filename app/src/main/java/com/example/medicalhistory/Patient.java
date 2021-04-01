package com.example.medicalhistory;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private String firstName;
    private String lastName;
    private Date birthday; //Do we know how to use this?
    private String condition;

    //constructor
//    public Patient(String fName, String lName, Date birthday, String condition){
//        this.firstName = fName;
//        this.lastName = lName;
//        this.birthday = birthday;
//        this.condition = condition;
//    }

    public Patient(EditText patientPopup_firstName, EditText patientPopup_lastName, EditText patientPopup_condition) {

        this.firstName = patientPopup_firstName.getText().toString();
        this.lastName = patientPopup_lastName.getText().toString();
        this.condition = patientPopup_condition.getText().toString();
    }

    public Patient(String firstName, String lastName, String condition){
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
    }

    public Patient(String name){
        List<String> firstName = new ArrayList<String>();
        List<String> lastName = new ArrayList<String>();

        for(int i = 0; i < name.length(); i++){
            boolean hitSpace = false;

            if(!hitSpace && name.charAt(i) != ' '){
                firstName.add(Character.toString(name.charAt(i)));
            }else if(name.charAt(i) == ' '){
                hitSpace = true;
            }else{
                lastName.add(Character.toString(name.charAt(i)));
            }
        }
    }
}
