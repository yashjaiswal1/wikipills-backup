package com.example.wikipill;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    CardView CurrentDiagnosis,CaseHistory,MedicineLog,HeartRateMonitor;


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

}
