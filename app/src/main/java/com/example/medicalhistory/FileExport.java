package com.example.medicalhistory;

public class FileExport implements Runnable{
    private Page page;
    private String fileReffrance;

    @Override
    public void run() {

    }

    public void exportPage(){}
    public void setPage(Page page){this.page = page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
