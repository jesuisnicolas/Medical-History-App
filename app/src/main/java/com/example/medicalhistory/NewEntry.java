package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewEntry extends AppCompatActivity {
/*Here's where the user add a new Entry to the Medical History.
Each new entry should be stored in an Array linked to the specific user (patient)
who created it
 */
    public String patientWorkingDir;
    public String filePath;
    Button selectFileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        patientWorkingDir = message;


        //this is supposed to work to select a file in the phone
        //but I still don't know how to retrieve the path to the file.
        final int PICKFILE_REQUEST_CODE = 8778;
        selectFileBtn = findViewById(R.id.selectFileBtn);
        selectFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, PICKFILE_REQUEST_CODE);
                filePath = ""; //here the path to the file should be assigned.
            }
        });
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
        String filePath; //I haven't done the part were we get the path. line 33

        //And now here the file should be created in the patient's folder

    }
}