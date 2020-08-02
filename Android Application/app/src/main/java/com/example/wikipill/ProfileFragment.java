package com.example.wikipill;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    BarChart barChart;
    LineChart lineChart;
    PieChart pieChart;
    Typeface dys;

    LinearLayout personalinfo, experience, review;
    TextView personalinfobtn, experiencebtn, reviewbtn,pr1,pr2,pr3,pr4,pr5,pr6,pr7,pr8,pr9,pr10,pr11,pr12,pr13,pr14,pr15,pr16,pr17,pr18,pr19,pr21,pr20;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        personalinfo = view.findViewById(R.id.personalinfo);
        //experience = view.findViewById(R.id.experience);
        review = view.findViewById(R.id.review);
        personalinfobtn = view.findViewById(R.id.personalinfobtn);
        reviewbtn = view.findViewById(R.id.reviewbtn);
        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
//        experience.setVisibility(View.GONE);
        review.setVisibility(View.GONE);

        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                //experience.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                reviewbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });


        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);

                review.setVisibility(View.VISIBLE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                reviewbtn.setTextColor(getResources().getColor(R.color.blue));

            }
        });

        final  global abc = (global) getActivity().getApplicationContext();

        pr1 = view.findViewById(R.id.pr1);
        pr2 = view.findViewById(R.id.pr2);
        pr3 = view.findViewById(R.id.pr3);
        pr4 = view.findViewById(R.id.pr4);
        pr5 = view.findViewById(R.id.pr5);
        pr6 = view.findViewById(R.id.pr6);
        pr7 = view.findViewById(R.id.pr7);
        pr8 = view.findViewById(R.id.pr8);
        pr9 = view.findViewById(R.id.pr9);
        pr10 = view.findViewById(R.id.pr10);
        pr11 = view.findViewById(R.id.pr11);
        pr12 = view.findViewById(R.id.pr12);
        pr13 = view.findViewById(R.id.pr13);
        pr14 = view.findViewById(R.id.pr14);
        pr15 = view.findViewById(R.id.pr15);
        pr16 = view.findViewById(R.id.pr16);
        pr17 = view.findViewById(R.id.pr17);
        pr18 = view.findViewById(R.id.pr18);
        pr19 = view.findViewById(R.id.pr19);
        pr20 = view.findViewById(R.id.pr20);
        pr21 = view.findViewById(R.id.pr21);


        if (abc.getLanguage() == 2) {
            dys = ResourcesCompat.getFont(getActivity(), R.font.dyslexic);

            pr1.setTypeface(dys);
            pr2.setTypeface(dys);
            pr3.setTypeface(dys);
            pr4.setTypeface(dys);
            pr5.setTypeface(dys);
            pr6.setTypeface(dys);
            pr7.setTypeface(dys);
            pr8.setTypeface(dys);
            pr9.setTypeface(dys);
            pr10.setTypeface(dys);
            pr11.setTypeface(dys);
            pr12.setTypeface(dys);
            pr13.setTypeface(dys);
            pr14.setTypeface(dys);
            pr15.setTypeface(dys);
            pr16.setTypeface(dys);
            pr17.setTypeface(dys);
            pr18.setTypeface(dys);
            pr19.setTypeface(dys);
            pr20.setTypeface(dys);
            pr21.setTypeface(dys);
        }







        return view;

    }


}
