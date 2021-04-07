package com.example.medicalhistory;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class Email extends AppCompatActivity {
    private List<Page> pages = new ArrayList<Page>();
    private String emailAdress;

    public Email(List<Page> pages, String emailAdress){
        this.pages = pages;
        this.emailAdress = emailAdress;
    }

    public void sendEmail(){
        Intent email = new Intent(Intent.ACTION_SEND);

        String message = new String();
        for(int i = 0; i < pages.size(); i++){
            message = message + "\n" + pages.get(i).getExtraInfo();
        }


        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");

        email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAdress});
        email.putExtra(Intent.EXTRA_SUBJECT, "Medical History");
        email.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(email);
    }

}
