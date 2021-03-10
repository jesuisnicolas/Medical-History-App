package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PageActivity extends AppCompatActivity {

    Page page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
    }

    public void export(){}
}