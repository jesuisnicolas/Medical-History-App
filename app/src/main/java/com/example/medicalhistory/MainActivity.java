package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Jack was here
    //No he wasn't


    private String TAG = "MainActivity";
    private List<Page> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData(this);
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

}