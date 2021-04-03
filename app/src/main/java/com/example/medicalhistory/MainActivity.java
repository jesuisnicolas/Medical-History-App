package com.example.medicalhistory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.SharedPreferences.*;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    public List<Page> pages;

    public static final String EXTRA_MESSAGE = "com.example.medicalhistory.MESSAGE";
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

        //This will populate the list from the sharedpreferences file
//        populatePatientsList(); //But it makes the app crash

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
        Editor editor = sharedPreferences.edit();

//        editor.putString("UserName", "joe");
        editor.apply();
//        Log.i("mainActivity", "Data saved");
    }

    public void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        String json = sharedPreferences.getString("Patients","");
        Log.i(TAG, "loaded patients: " + json);
        Gson gson = new Gson();
        String[] names = gson.fromJson(json, String[].class);
        if(names[0] != "") {
            for (int i = 0; i < names.length; i++) {
                patients.add(new Patient(names[i]));
            }
        }
        //TODO: this should be a function but im lazy
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ListView listView = (ListView) findViewById(R.id.PatientListView);
        listView.setAdapter(patientAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                String EXTRA_MESSAGE = "com.example.medicalhistory.MESSAGE";
                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                String message = parent.getAdapter().getItem(position).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
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
        Log.i(TAG,"list of patients is as follows: ");
        for(int i = 0; i < patients.size(); i++){
            names[i] = patients.get(i).getFirstName() + " " + patients.get(i).getLastName();
            Log.i(TAG,names[i]);
            String dirName = (patients.get(i).getFirstName() + patients.get(i).getLastName()).toLowerCase().toString();
            getDir(dirName, MODE_PRIVATE);
        }

        //convert the list of names into the listView
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ListView listView = (ListView) findViewById(R.id.PatientListView);
        listView.setAdapter(patientAdapter);

        //this stores the patient array in SharedPreferences
        //TODO: this should be moved to the 'saveData' function as this is its intended function.
        // also this operation runs every time we add a user when it realy only needs to run when the app is closing
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(names);
        Editor editor = sharedPreferences.edit();
        editor.putString("Patients", json );
        editor.commit();
        Log.i(TAG,"saved patients: " + json);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                String EXTRA_MESSAGE = "com.example.medicalhistory.MESSAGE";
                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                String message = parent.getAdapter().getItem(position).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

    }

//    public void populatePatientsList() {
//        try {
//            Gson gson = new Gson();
//            SharedPreferences sh = getSharedPreferences("PATIENTS", MODE_PRIVATE);
//            String names = gson.fromJson(String.valueOf(sh), String.class);
//            ArrayAdapter<String> patientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
//            ListView listView = (ListView) findViewById(R.id.PatientListView);
//            listView.setAdapter(patientAdapter);
//        } finally {
//            return;
//        }
//    }


}
