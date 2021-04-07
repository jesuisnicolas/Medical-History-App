package com.example.medicalhistory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;

public class PageActivity extends AppCompatActivity {

    private static final String TAG = "PageActivity";
    protected String pathName;
    protected String onlyFileName;
    protected Page page;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Intent intent = getIntent();
        String message = intent.getStringExtra(PatientActivity.EXTRA_MESSAGE);

//        pathName =  getDir(message, MODE_PRIVATE) ; //TODO: get file extention here
        File file = getDir(message, MODE_PRIVATE);
        onlyFileName = message;
        pathName = file.getPath();
        pathName = message;
        Log.d(TAG,"Opening file: " + pathName);
        try {
            openFile();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        FileImport fileImport = new FileImport(pathName);
//        try {
//            fileImport.importFile();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        page = fileImport.getPage();
//
//        TextView title = findViewById(R.id.fileName);
////        title.setText(page.getCondition());
//
//        EditText body = findViewById(R.id.bodyText);
////        body.setText(page.getExtraInfo());

    }
    public void openFile() throws JSONException {
        Gson gson = new Gson();

        File buf = getDir(onlyFileName, MODE_PRIVATE);

        JSONObject obj = new JSONObject((Map) buf);

        String condition = obj.getString("condition");
        String doctor = obj.getString("doctor");
        String date = obj.getString("date");
        String extraInfo = obj.getString("extraInfo");



//        BufferedReader buf = null; //this seems to work
//        try {
//            buf = new BufferedReader(new FileReader(pathName));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        page = gson.fromJson(buf, Page.class);


        TextView title = findViewById(R.id.fileName);
        title.setText(condition);
//        title.setText("Esta es un aprueba a mano");

        EditText body = findViewById(R.id.bodyText);
        body.setText(extraInfo);
//            body.setText("hola que tal, como estas, todo bien?");
//        this is a long shot... but lets see...
//            page = new Gson().fromJson(buf, Page.class);
//            Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
//            Map<String, String[]> patientEntry = new Gson().fromJson(buf, mapType);

//            Log.i(TAG, "importFile: " + buf);


    }


    @Override
    protected void onPause() {
        super.onPause();
        save();
    }
    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    protected void save(){
        Runnable runnable = new FileExport(page, pathName);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void export(){}
}