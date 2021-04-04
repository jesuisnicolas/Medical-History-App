package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class NewEntry extends AppCompatActivity {
    private static final String TAG = "New Entry Activity";
    /*Here's where the user add a new Entry to the Medical History.
    Each new entry should be stored in an Array linked to the specific user (patient)
    who created it
     */
    public String patientWorkingDir;
    public String filePath;
    private TextView newEntryFilePath;
    Button selectFileBtn;

    //this is supposed to work to select a file in the phone
    //but I still don't know how to retrieve the path to the file.
    final int PICKFILE_REQUEST_CODE = 89; //This will be used by the function that looks for the file URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        patientWorkingDir = message;

        selectFileBtn = findViewById(R.id.selectFileBtn);
        newEntryFilePath = findViewById(R.id.newEntryFilePath);
        selectFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callChooseFileFromDevice();
            }
        });
    }

    private void callChooseFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE);

        chooseFile.setType("*/*");
        startActivityForResult(chooseFile, PICKFILE_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //the result data contains a URI for the document or directory the user selected
            if (resultData != null) {

                Log.d(TAG, "onCreate: " + resultData.getData());
                Intent newIntent = new Intent();
                newIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, String.valueOf(resultData.getData()));
                setResult(RESULT_OK, newIntent);
                finish();
                newEntryFilePath.setText(resultData.getData().toString());
            }
        }
    }

    /**
     * This Method will capture the information entered by the user
     * and store it in the Json file as a new entry.
     * @param view
     */
    public void createNewEntry(View view){

        EditText cond = (EditText) this.findViewById(R.id.newEntryCondition);
        EditText doc = (EditText) this.findViewById(R.id.newEntryDoctorName);
        EditText dat = (EditText) this.findViewById(R.id.newEntryDate);
        EditText inf = (EditText) this.findViewById(R.id.newEntryInfo);

        String condition = cond.getText().toString();
        String doctor = doc.getText().toString();
        String date = dat.getText().toString();
        String information = inf.getText().toString();
        String newEntryFilePath; //I haven't done the part were we get the path.
        //And now here the file should be created in the patient's folder

        Page file = new Page();
        file.setBodyText("hello");
        file.setFileRefrance("text.txt");
//        file.setCondition(condition);
//        file.setDoctor(doctor);
//        file.setDate(date);
//        file.setExtraInfo(information);

//        FileExport fileExport = new FileExport(file, getFilesDir() + "/text.txt");
//        fileExport.exportPage();

        Runnable runnable = new FileExport(file, getFilesDir() + "/text.txt"); //"app_"+patientWorkingDir+"/"+condition);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

