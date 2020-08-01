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
            MedicineName.setText("Fexofay-A");
            Dose.setText("1 Per Day");
            Time.setText("Bed Time");
            Quantity.setText("Toatl 56 capsules | 42 capsules left");

        }else if (s.equals("2")){
            MedicineName.setText("Saridon");
            Dose.setText("2 Per Day");
            Time.setText("1 Morning | 1 Evening");
            Quantity.setText("Toatl 112 capsules | 84 capsules left");
        }else if (s.equals("3")){
            MedicineName.setText("Paracip-500");
            Dose.setText("Every 6 Hour");
            Time.setText("6 AM | 12 PM | 6 PM | 12 AM");
            Quantity.setText("Toatl 224 capsules | 168 capsules left");
        }else if (s.equals("4")){
            MedicineName.setText("");
            Dose.setText("");
            Time.setText("");
            Quantity.setText("");
        }


    }
}