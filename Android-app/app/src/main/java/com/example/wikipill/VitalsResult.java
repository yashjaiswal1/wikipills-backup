package com.example.wikipill;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VitalsResult extends Fragment {
        TextView ResultBPM;
        TextView resultSPo2;
        int bp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_vitals_result, container, false);

        String BPM = getArguments().getString("BPM");
        String SPO2 = getArguments().getString("SPO2");



        ResultBPM = view.findViewById(R.id.resultBPM);
        ResultBPM.setText(BPM);
        resultSPo2 = view.findViewById(R.id.resultSPo2);
        resultSPo2.setText(SPO2);



        Updatebpm bpm = new Updatebpm();
        bpm.execute(Integer.parseInt(BPM));
        Updatespo2 spo2 = new Updatespo2();
        spo2.execute(Integer.parseInt(SPO2));

        return  view;
    }


    private class Updatebpm extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.updateBPM(integers[0]);
            return null;
        }
    }
    private class Updatespo2 extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.updateSpo2(integers[0]);
            return null;
        }
    }
}