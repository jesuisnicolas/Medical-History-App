package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private static final String TAG = "Patient Activity";
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
        // This was an attempt to remove the extensions that didn't work. Moving on...
//        for (String path :fileNames){
//            final int lastPeriodPos = path.lastIndexOf('.');
//            // Remove the last period and everything after it
//            File renamed = new File(f.getParent(), path.substring(0, lastPeriodPos));
//            path = renamed.getPath();
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, fileNames);

        ListView listView = (ListView) findViewById(R.id.fileList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View PatientActivity, int position,
                                    long id) {
                Intent intent = new Intent(PatientActivity.this, PageActivity.class);
                String message = parent.getAdapter().getItem(position).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                Log.d(TAG, "onItemClick: "+ message);
                startActivity(intent);
            }
        });

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