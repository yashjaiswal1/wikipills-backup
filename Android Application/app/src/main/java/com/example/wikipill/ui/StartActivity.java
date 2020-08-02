package com.example.wikipill.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.wikipill.Constants;
import com.example.wikipill.R;
import com.example.wikipill.core.Accelerometer;
import com.example.wikipill.core.Plot;
import com.github.mikephil.charting.charts.LineChart;

import java.text.DateFormat;
import java.util.Date;

public class StartActivity extends AppCompatActivity {

    public static final String TAG = "StartActivity";

    private SwitchCompat mSwitchCompat;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private LineChart mLineChart;
    private Plot mPlot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSwitchCompat =  findViewById(R.id.switch1);

        mLineChart =  findViewById(R.id.chart);


        mPlot = new Plot(mLineChart);
        mPlot.setUp();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final Accelerometer accelerometer = new Accelerometer(mSensorManager, mSensor, mHandler);

        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    accelerometer.startListening();
                } else {
                    accelerometer.stopListening();
                }
            }
        });


    }



    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.MESSAGE_CHANGED:
                    float value = msg.getData().getFloat(Constants.VALUE);
                    mPlot.addEntry(value);

                    break;
                case Constants.MESSAGE_EMERGENCY:
                     Toast.makeText(StartActivity.this, "Notification sent to dashboard", Toast.LENGTH_SHORT).show();




                    // stop listening the sensor
                    mSwitchCompat.setChecked(false);

                    break;
                case Constants.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(),msg.getData().getString(Constants.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
