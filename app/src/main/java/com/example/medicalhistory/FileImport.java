package com.example.medicalhistory;

public class FileImport implements Runnable {
    private Page page;
    private String fileReffrance;

    @Override
    public void run() {

    }

    public FileImport(){
        page = new Page();
    }

    public void importFile(){}
    public Page getPage(){return page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
