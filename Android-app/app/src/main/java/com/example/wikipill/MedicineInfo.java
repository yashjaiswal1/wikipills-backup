package com.example.wikipill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicineInfo extends AppCompatActivity {

    ImageView back;
    TextView MedicineName,Dose,Time,Quantity;
    String s = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        back = findViewById(R.id.back);
        MedicineName = findViewById(R.id.med1_name);
        Dose = findViewById(R.id.med2_name);
        Time = findViewById(R.id.med3_name);
        Quantity = findViewById(R.id.text7);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        s = intent.getStringExtra("MedicineName");
        Log.i("Intent",s);

        if (s.equals("1")){
            MedicineName.setText(R.string.ff1);
            Dose.setText(R.string.ff2);
            Time.setText(R.string.ff3);
            Quantity.setText(R.string.ff4);

        }else if (s.equals("2")){
            MedicineName.setText(R.string.ss1);
            Dose.setText(R.string.ss2);
            Time.setText(R.string.ss3);
            Quantity.setText(R.string.ss4);
        }else if (s.equals("3")){
            MedicineName.setText("Evion-600");
            Dose.setText("2 Times a day");
            Time.setText("Before Lunch | Before Dinner");
            Quantity.setText("Toatl 224 capsules | 168 capsules left \n Expiry Date: 5-07-2020");
        }else if (s.equals("4")){
            MedicineName.setText("");
            Dose.setText("");
            Time.setText("");
            Quantity.setText("");
        }


    }
}