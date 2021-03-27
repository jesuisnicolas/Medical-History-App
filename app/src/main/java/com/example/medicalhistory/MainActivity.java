package com.example.medicalhistory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    public List<Page> pages;

    //container for the patients
    //when we create a patient we should store it in an array in the app storage
    ViewGroup patientsContainer;

    //variables to create the dialog to create a new patient
    private AlertDialog.Builder patientInput;
    private AlertDialog dialog;
    private EditText patientPopup_firstName, patientPopup_lastName, patientPopup_birthday, patientPopup_condition;
    private Button patientPopup_cancel, patientPopup_save;

    private List<Patient> patients = new ArrayList<Patient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData(this);

        patientsContainer = findViewById(R.id.patientsContainer);
    }

    public Thread importPages(){
        FileImport r = new FileImport("",getFilesDir().toString());
        Thread thread = new Thread(r , "File Import Thread");
        thread.start();
        return thread;
    }
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
                Log.i(TAG, "Adding new Patient with name " + patientPopup_firstName.getText().toString());
                Patient newPatient = new Patient(patientPopup_firstName, patientPopup_lastName, patientPopup_condition);
                patients.add(newPatient);
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
        //define the list of names we are working with
        Log.i(TAG, "adding new patient to list");
        String[] names = new String[patients.size()];
        for(int i = 0; i < patients.size(); i++){
            names[i] = patients.get(i).getFirstName();
        }

        //convert the list of names into the listView
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ListView listView = (ListView) findViewById(R.id.PatientListView);
        listView.setAdapter(patientAdapter);
    }



}