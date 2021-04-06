package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
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
    public static final String EXTRA_MESSAGE = "com.example.medicalhistory.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        //TODO: set working directy from activity intention message
        Intent intent = getIntent();
        //this message contains the name and surname of patient
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.patientNameH1);
        textView.setText(message);
        workingDirectory = message.toLowerCase().replaceAll("\\s+",""); //This makes the directory with the name received
        populateList(this);
    }


    private void populateList(PatientActivity view){
        File f = getDir(workingDirectory, MODE_PRIVATE);
        String[] fileList = f.list();
        //TODO: decide on removing file extentions (.txt / .json)
        fileNames = Arrays.asList(fileList);
        //this is an attempt
//        Field[] fields=R.class.getFields();
//        for(int count=0; count < fields.length; count++){
//            Log.i("Raw Asset: ", fields[count].getName());
//        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, fileNames);

        ListView listView = (ListView) findViewById(R.id.fileList);
        listView.setAdapter(adapter);

    }

    public void addNewEntry(View view){
        Intent intent = new Intent(this, NewEntry.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, workingDirectory);
        startActivity(intent);
    }



    //TODO: make on clickevent for list
}