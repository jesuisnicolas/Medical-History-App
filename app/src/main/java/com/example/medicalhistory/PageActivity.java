package com.example.medicalhistory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class PageActivity extends AppCompatActivity {

    private static final String TAG = "PageActivity";
    protected String pathName;
    protected Page page;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Intent intent = getIntent();
        String message = intent.getStringExtra(PatientActivity.EXTRA_MESSAGE);
        Log.d(TAG,"Opening file: " + pathName);
        //pathName =  getDir(message, MODE_PRIVATE) ; //TODO: get file extention here
        File file = getDir(message, MODE_PRIVATE);
        pathName = file.getPath();


        FileImport fileImport = new FileImport(pathName);
        fileImport.importFile();
        page = fileImport.getPage();

        TextView title = findViewById(R.id.fileName);
        title.setText(page.getCondition());

        EditText body = findViewById(R.id.bodyText);
        body.setText(page.getExtraInfo());
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