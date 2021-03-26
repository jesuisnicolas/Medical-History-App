package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewEntry extends AppCompatActivity {
/*Here's where the user add a new Entry to the Medical History.
Each new entry should be stored in an Array linked to the specific user (patient)
who created it
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
    }
}