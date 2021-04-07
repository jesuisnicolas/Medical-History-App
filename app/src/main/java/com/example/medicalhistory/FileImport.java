package com.example.medicalhistory;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileImport implements Runnable {
    private Page page;
    private List<FileImport> pages = null;
    private String fileReffrance;
    private String TAG = "FileImport";
    private WeakReference<Activity> activityWeakReference;
    private String workingDirectroy;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        //depreciated function no longer necisary
//        Log.i(TAG, "Thread Created");
//
//        File f = new File(String.valueOf(workingDirectroy));
//        String[] pathnames = f.list();
//
//        for(int i = 0; i < pathnames.length; i++){
//            pages.add(new FileImport());
//            pages.get(i).setFileReffrance(pathnames[i]);
//            pages.get(i).importFile();
//        }
        //all pages have been imported. now to push them to the UIThread

    }

    public FileImport(String pathname){
        page = new Page();
        this.workingDirectroy = pathname;

    }
    public FileImport() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void importFile() throws FileNotFoundException {

//            Reader reader = Files.newBufferedReader(workingDirectroy, StandardCharsets.UTF_8);
            Gson gson = new Gson();

            BufferedReader buf = new BufferedReader(new FileReader(workingDirectroy)); //this seems to work
            Log.i(TAG, "importFile: THe importing directory is" + workingDirectroy);
            page = gson.fromJson(buf, Page.class);

            //this is a long shot... but lets see...
//            page = new Gson().fromJson(buf, Page.class);
//            Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
//            Map<String, String[]> patientEntry = new Gson().fromJson(buf, mapType);

//            Log.i(TAG, "importFile: " + buf);


    }
    public Page getPage(){return page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
