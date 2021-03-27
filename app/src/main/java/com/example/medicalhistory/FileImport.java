package com.example.medicalhistory;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

public class FileImport implements Runnable {
    private Page page;
    private List<FileImport> pages = null;
    private String fileReffrance;
    private String TAG = "FileImport";
    private WeakReference<Activity> activityWeakReference;
    private String workingDirectroy;


    @Override
    public void run() {
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

    public FileImport(String pathname, String workingDirectory){
        page = new Page();
        this.workingDirectroy = workingDirectory;
    }
    public FileImport() {

    }
    public void importFile(){}
    public Page getPage(){return page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
