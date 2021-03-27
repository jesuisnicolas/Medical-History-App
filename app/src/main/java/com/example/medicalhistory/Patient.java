package com.example.medicalhistory;

import android.widget.EditText;

import java.util.Date;

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
}
