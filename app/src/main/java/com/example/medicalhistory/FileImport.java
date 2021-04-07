package com.example.medicalhistory;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Log.i(TAG, "Thread Created");

        File f = new File(String.valueOf(workingDirectroy));
        String[] pathnames = f.list();

        for(int i = 0; i < pathnames.length; i++){
            pages.add(new FileImport());
            pages.get(i).setFileReffrance(pathnames[i]);
            pages.get(i).importFile();
        }
        //all pages have been imported. now to push them to the UIThread

    }

    public FileImport(String pathname){
        page = new Page();
        this.workingDirectroy = pathname;

    }
    public FileImport() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void importFile(){
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileReffrance));
            Page newPage = gson.fromJson(reader, Page.class);
            page = newPage;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Page getPage(){return page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
