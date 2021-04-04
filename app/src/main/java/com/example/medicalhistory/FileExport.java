package com.example.medicalhistory;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExport implements Runnable{
    private Page page;
    private String fileReffrance;


    @Override
    public void run() {
        exportPage();
        return;
    }

    public void exportPage(){
        Gson gson = new Gson();
        String json = gson.toJson(page);

        File output = new File(fileReffrance);
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileExport(Page page, String fileReffrance){this.page = page; this.fileReffrance = fileReffrance;}
    public void setPage(Page page){this.page = page;}
    public void setFileReffrance(String reffrance){fileReffrance = reffrance;}
}
