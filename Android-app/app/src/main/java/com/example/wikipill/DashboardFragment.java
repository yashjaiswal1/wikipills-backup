package com.example.wikipill;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    CardView CurrentDiagnosis,CaseHistory,MedicineLog,HeartRateMonitor;
    String sSavedLocale = "en";



    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);

        CurrentDiagnosis = rootview.findViewById(R.id.CurrentDiagnosis);
        CaseHistory = rootview.findViewById(R.id.CaseHistory);
        MedicineLog = rootview.findViewById(R.id.MedicineLog);
        HeartRateMonitor = rootview.findViewById(R.id.heartRateMonitor);
        HeartRateMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommonActivity.class);
                intent.putExtra("Fragment","4");
                startActivity(intent);
            }
        });
        CurrentDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommonActivity.class);
                intent.putExtra("Fragment","1");
                startActivity(intent);
            }
        });
        CaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommonActivity.class);
                intent.putExtra("Fragment","2");
                startActivity(intent);
            }
        });

        MedicineLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommonActivity.class);
                intent.putExtra("Fragment","3");
                startActivity(intent);
            }
        });



        return rootview;

    }

    private void setAppLocale (String localeCode){
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
        getActivity().recreate();
    }




}
