package com.example.wikipill.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.wikipill.Constants;
import com.example.wikipill.R;
import com.example.wikipill.core.Accelerometer;
import com.example.wikipill.core.Plot;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Date;

public class StartActivity extends AppCompatActivity {

    public static final String TAG = "StartActivity";

    private SwitchCompat mSwitchCompat;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    MediaPlayer mp;

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
        public void handleMessage(final Message msg) {
            float value = msg.getData().getFloat(Constants.VALUE);
            switch (msg.what) {
                case Constants.MESSAGE_CHANGED:

                    Log.i("Value",Float.toString(value));
                    mPlot.addEntry(value);

                    break;
                case Constants.MESSAGE_EMERGENCY:
//                     Toast.makeText(StartActivity.this, "Notification sent to dashboard", Toast.LENGTH_SHORT).show();


                    final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(
                            StartActivity.this,R.style.BottomSheetDialogTheme
                    );
                    final View bottomSheetView = LayoutInflater.from(StartActivity.this.getApplicationContext())
                            .inflate(
                                    R.layout.fall_alert_bottom_sheet,
                                    (RelativeLayout)findViewById(R.id.bottomSheetContainer2)
                            );

                    int ResID;
                    ResID = R.raw.alert;
                    if(mp!=null) {
                        if(mp.isPlaying())
                            mp.stop();
                        mp.reset();
                        mp.release();
                        mp=null;
                    }

                    mp = MediaPlayer.create(StartActivity.this, ResID);
                    mp.start();


//                    if ((value >6 && value<8 ) || (value>10 && value < 20)){
//                        Log.i("value2","entered"+Float.toString(value));
//
//                        mp.release();
//
//                    }

//            handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                        }
//                    }, 20 * 1000);

                    bottomSheetView.findViewById(R.id.dismis).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mp.release();
                            mp=null;
                            bottomSheetDialog.dismiss();
                        }
                    });
                    bottomSheetView.findViewById(R.id.call1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:+6350118204"));//change the number.
                            startActivity(callIntent);
                            mp.release();
                            bottomSheetDialog.dismiss();
                        }
                    });
                    bottomSheetView.findViewById(R.id.call2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:+9826151216"));//change the number.
                            startActivity(callIntent);
                            mp.release();
                            bottomSheetDialog.dismiss();
                        }
                    });


                    bottomSheetDialog.setContentView(bottomSheetView);
                    bottomSheetDialog.show();


                    // stop listening the sensor


                    break;
                case Constants.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(),msg.getData().getString(Constants.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    void stopfunc(){
        mp.stop();
    }

}
