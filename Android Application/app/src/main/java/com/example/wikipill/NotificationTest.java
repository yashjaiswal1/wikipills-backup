package com.example.wikipill;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class NotificationTest extends AppCompatActivity {
    TextView SelectedLanguage;
    int i =0;
    String pass;
    int lan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        i=1;

       findViewById(R.id.geofenceAlert).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                ViewDialog dialog = new ViewDialog();

               if (i==1) {
                   pass = "Please don't forget your wallet,keys or any other essential items.";
                   lan =2 ;
               }
                dialog.showDialog(NotificationTest.this,pass,lan);
           }
       });
        findViewById(R.id.weatherNotif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==1) {
                    pass = "It might rain today. Don't forget to carry your umberella.";
                    lan = 5;
                }else{
                    pass = "आज बारिश हो सकती है। अपना छाता ले जाना मत भूलियेगा ।";
                    lan = 1;
                }
                ViewDialog dialog = new ViewDialog();
                dialog.showDialog(NotificationTest.this,pass,lan);
            }
        });

        SelectedLanguage = findViewById(R.id.selectedLanguage);

        SelectedLanguage = findViewById(R.id.selectedLanguage);




        SelectedLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(NotificationTest.this);
                dialog.setContentView(R.layout.language_dialog);
                dialog.setCancelable(true);
                final TextView English = dialog.findViewById(R.id.english);
                dialog.findViewById(R.id.englishDyslexic).setVisibility(View.GONE);
                final TextView hindi = dialog.findViewById(R.id.hindi);
                dialog.findViewById(R.id.kannada).setVisibility(View.GONE);

                English.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i = 1;
                        SelectedLanguage.setText("English");
                        dialog.cancel();
                    }
                });

                hindi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        i = 2;
                        SelectedLanguage.setText("हिंदी ");
                        dialog.cancel();
                    }
                });


                dialog.show();

            }
        });

        if (i==1){
            SelectedLanguage.setText(R.string.ENG);
        } else if (i==2) {
            SelectedLanguage.setText("हिन्दी");
        }

    }



}