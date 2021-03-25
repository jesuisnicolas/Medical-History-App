package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


// This activity will be called when the user taps in the name of the patient
// in the Main Activity.
public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
    }
}