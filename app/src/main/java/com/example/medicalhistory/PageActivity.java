package com.example.medicalhistory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PageActivity extends AppCompatActivity {

    protected String pathName;
    protected Page page;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        pathName = ""; //TODO: get file extention here

        FileImport fileImport = new FileImport(pathName);
        fileImport.importFile();
        page = fileImport.getPage();

        TextView title = findViewById(R.id.fileName);
        title.setText(page.getTitle());

        EditText body = findViewById(R.id.bodyText);
        body.setText(page.getBodyText());
    }

    public void export(){}
}