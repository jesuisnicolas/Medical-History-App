package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.Arrays;
import java.util.List;


// This activity will be called when the user taps in the name of the patient
// in the Main Activity.
public class PatientActivity extends AppCompatActivity {
/*This is the page where the page can see their last entries, change its info, etc.
We should store all the info in an array.
The latest entries are obtained from the app storage. Also, the doctors names and the
condition name should be stored.
 */

    protected String workingDirectory;
    protected List<String> fileNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        //TODO: set working directy from activity intention message
    }


    protected void populateList(View view){
        File f = new File(workingDirectory);
        String[] fileList = f.list();
        //TODO: decide on removing file extentions (.txt / .json)
        fileNames = Arrays.asList(fileList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, fileNames);

        ListView listView = (ListView) findViewById(R.id.fileList);
        listView.setAdapter(adapter);
    }

    //TODO: make on clickevent for list
}