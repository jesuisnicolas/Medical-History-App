package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class fullHistory extends AppCompatActivity {
/*Here's where the user can see his full history. Each entry is an object, and
the Recycler View should allow the user to tap one of the entries and see the details in
a new activity. Also, it can add and export from here.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_history);
    }
}