package com.example.medicalhistory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Jack was here
    //No he wasn't


    private String TAG = "MainActivity";
    private List<Page> pages;

    //container for the patients
    //when we create a patient we should store it in an array in the app storage
    ViewGroup patientsContainer;

    //variables to create the dialog to create a new patient
    private AlertDialog.Builder patientInput;
    private AlertDialog dialog;
    private EditText patientPopup_firstName, patientPopup_lastName, patientPopup_birthday, patientPopup_condition;
    private Button patientPopup_cancel, patientPopup_save;

    private Patient newPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData(this);

        patientsContainer = findViewById(R.id.patientsContainer);
    }

    public void importPages(){}
    public void exportPages(){}

    public void deletePage(View view){}
    public void openPage(View view){}



    public static void saveData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        editor.putString("UserName", "joe");
        editor.apply();
//        Log.i("mainActivity", "Data saved");
    }

    public static void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
//        UserName = sharedPreferences.getString("UserName", "");
//        Log.i("mainActivity", "Data loaded: " + UserName);
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveData(this);
    }

//    public void addPatient(View view) {
//        TextView patient = new TextView(this);
//        patient.setText("New Patient");
//        patientsContainer.addView(patient);
//    }

    public void createNewPatient(View view){
        patientInput = new AlertDialog.Builder(this);
        final View patientPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        patientPopup_firstName = (EditText) patientPopupView.findViewById(R.id.newPatientFirstName);
        patientPopup_lastName = (EditText) patientPopupView.findViewById(R.id.newPatientLastName);
//        patientPopup_birthday = (EditText) patientPopupView.findViewById(R.id.newPatientBirthday);
        patientPopup_condition = (EditText) patientPopupView.findViewById(R.id.newPatientCondition);
        patientPopup_cancel = (Button) patientPopupView.findViewById(R.id.newPatientCancelBtn);
        patientPopup_save = (Button) patientPopupView.findViewById(R.id.newPatientSaveBtn);

        patientInput.setView(patientPopupView);
        dialog = patientInput.create();
        dialog.show();

        patientPopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        patientPopup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPatient = new Patient(patientPopup_firstName, patientPopup_lastName, patientPopup_condition);
                addPatient();
//                TextView patient;
//                patient = new TextView(v);
//                patient.setText(newPatient.getFirstName());
//                patientsContainer.addView(patient);
                dialog.dismiss();
            }

        });
    }

    public void addPatient() {
        TextView patient = new TextView(this);
        patient.setText(newPatient.getFirstName());
        patientsContainer.addView(patient);
    }



}