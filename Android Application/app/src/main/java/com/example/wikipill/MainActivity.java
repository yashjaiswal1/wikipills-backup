package com.example.wikipill;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.wikipill.ui.StartActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Locale;

public class MainActivity extends LocalizationActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    LinearLayout linearLayout;
    TextView SelectedLanguage;
    String sSavedLocale = "en";
    Typeface dys;
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        linearLayout = findViewById(R.id.linear);
        frameLayout = findViewById(R.id.fragment_container);

        if (savedInstanceState==null){
            bottomNav.setItemSelected(R.id.input,true);
           fragmentManager = getSupportFragmentManager();
            ARFragment abc = new ARFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,abc).commit();


        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){

                    case R.id.dashboard:
                        fragment = new DashboardFragment();

                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();

                        break;
                    case R.id.input:
                        fragment = new ARFragment();
                        break;
                    case R.id.comunityBonding:
                        fragment = new SocializeFragment();
                        break;


                }

                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                } else if(i != R.id.dashboard && i != R.id.profile){
                    linearLayout.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                }
            }
        });

        // NOtification

        this.database = FirebaseDatabase.getInstance();
        this.reference = this.database.getReference("Notifications");
        this.reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().equals("1")) {
                    MainActivity.this.addNotification("WARNING!","You have stepped out of safe area.");
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final global global = (global) getApplicationContext();



        // Select Language Dialog

        SelectedLanguage = findViewById(R.id.selectedLanguage);

        if (global.getLanguage()==1){
            SelectedLanguage.setText(R.string.ENG);
        } else if (global.getLanguage()==2){
            SelectedLanguage.setText(R.string.DYSLEXIC);

        }else if (global.getLanguage()==3){
            SelectedLanguage.setText("हिन्दी");
        }else if (global.getLanguage()==4){
            SelectedLanguage.setText("ಕನ್ನಡ");
        }

        SelectedLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.language_dialog);
                    dialog.setCancelable(true);
                TextView English = dialog.findViewById(R.id.english);
                TextView Dyslexic = dialog.findViewById(R.id.englishDyslexic);
                TextView hindi = dialog.findViewById(R.id.hindi);
                TextView kannada = dialog.findViewById(R.id.kannada);

                English.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("en");
                       // setAppLocale("en");
                        global.setLanguage(1);
                        dialog.cancel();
                    }
                });
                Dyslexic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        global.setLanguage(2);
                        dialog.cancel();
                    }
                });
                hindi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //setAppLocale("hn");
                       // recreate();
                        setLanguage("hn");
                        global.setLanguage(3);
                        dialog.cancel();
                    }
                });
                kannada.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("kn");
                        //setAppLocale("kn");
                        global.setLanguage(4);
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });

        getDataFex get = new getDataFex();
        get.execute();
        getDataPara get2 = new getDataPara();
        get2.execute();
        getDataSara get3 = new getDataSara();
        get3.execute();


        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                UpdateTest update = new UpdateTest();
                update.execute(1);
            }
        });


    }

    public void addNotification(String head,String data) {

        Log.i(NotificationCompat.CATEGORY_MESSAGE, "notif");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID1").setSmallIcon(R.mipmap.ic_launcher_round).setContentTitle(head).setContentText(data).setVibrate(new long[]{1000, 3000, 500}).setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        StringBuilder sb = new StringBuilder();
        sb.append("android.resource://");
        sb.append(getPackageName());
        sb.append("/");
        sb.append(R.raw.danger);
        builder.setSound(Uri.parse(sb.toString()));
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID1", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            channel.setVibrationPattern(new long[]{1000, 1000});
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(-16711936);
            channel.setLockscreenVisibility(1);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(channel);
            StringBuilder sb = new StringBuilder();
            String str = "android.resource://";
            sb.append(str);
            sb.append(getPackageName());
            String str2 = "/";
            sb.append(str2);
            sb.append(R.raw.danger);
            channel.setSound(Uri.parse(sb.toString()), new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build());
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(getPackageName());
                sb2.append(str2);
                sb2.append(R.raw.danger);
                final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), Uri.parse(sb2.toString()));
                r.play();
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(4000);
                            if (r.isPlaying()) {
                                r.stop();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*private void setAppLocale (String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        }else {
            config.locale=new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(config,dm);
        //setContentView(R.layout.activity_main);
        MainActivity.this.recreate();
    }*/
    private void setAppLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My lang",lang);
        editor.apply();
    }

    public  void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My lang","");
        setAppLocale(language);
    }

    private class getDataFex extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            return databaseAccess.getfexofay();
        }

        @Override
        protected void onPostExecute(String s) {
            final global abc = (global) getApplicationContext();
            abc.setFex(s);
        }
    }
    private class getDataPara extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            return databaseAccess.getPara();
        }

        @Override
        protected void onPostExecute(String s) {
            final global abc = (global) getApplicationContext();
            abc.setPara(s);
        }
    }
    private class getDataSara extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            return databaseAccess.getsaridon();
        }

        @Override
        protected void onPostExecute(String s) {
            final global abc = (global) getApplicationContext();
            abc.setSari(s);
        }
    }
    private class UpdateTest extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            databaseAccess.updateTest(integers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }
    }
}
