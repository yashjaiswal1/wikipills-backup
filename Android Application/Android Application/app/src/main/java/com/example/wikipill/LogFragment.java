package com.example.wikipill;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {

    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;
    ConstraintLayout expandableView2;
    Button arrowBtn2;
    CardView cardView2;
    ConstraintLayout expandableView3;
    Button arrowBtn3;
    CardView cardView3;
    ConstraintLayout expandableView4;
    Button arrowBtn4;
    CardView cardView4;
    ConstraintLayout expandableView5;
    Button arrowBtn5;
    CardView cardView5;
    TextView MedName,SE1,SE2,SE3,SE4,SE5,SE6,SE7,SE8,SE9,SE10,SE11,desc,genins;
    String data ="0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_log, container, false);

        data = getArguments().getString("data");

        expandableView = rootview.findViewById(R.id.expandableView);
        arrowBtn = rootview.findViewById(R.id.arrowBtn);
        cardView = rootview.findViewById(R.id.cardView);
        expandableView2 = rootview.findViewById(R.id.expandableView2);
        arrowBtn2 = rootview.findViewById(R.id.arrowBtn2);
        cardView2 = rootview.findViewById(R.id.cardView2);
        expandableView3 = rootview.findViewById(R.id.expandableView3);
        arrowBtn3 = rootview.findViewById(R.id.arrowBtn3);
        cardView3 = rootview.findViewById(R.id.cardView3);
        expandableView4 = rootview.findViewById(R.id.expandableView4);
        arrowBtn4 = rootview.findViewById(R.id.arrowBtn4);
        cardView4 = rootview.findViewById(R.id.cardView4);
        expandableView5 = rootview.findViewById(R.id.expandableView5);
        arrowBtn5 = rootview.findViewById(R.id.arrowBtn5);
        cardView5 = rootview.findViewById(R.id.cardView5);

        MedName = rootview.findViewById(R.id.medicineName);
        SE1 = rootview.findViewById(R.id.se1);
        SE2 = rootview.findViewById(R.id.se2);
        SE3 = rootview.findViewById(R.id.se3);
        SE4 = rootview.findViewById(R.id.se4);
        SE5 = rootview.findViewById(R.id.se5);
        SE6 = rootview.findViewById(R.id.se6);
        SE7 = rootview.findViewById(R.id.se7);
        SE8 = rootview.findViewById(R.id.se8);
        SE9 = rootview.findViewById(R.id.se9);
        SE10 = rootview.findViewById(R.id.se10);
        SE11 = rootview.findViewById(R.id.se11);
        desc = rootview.findViewById(R.id.desc);
        genins = rootview.findViewById(R.id.generalInst);

        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        arrowBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView2.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        arrowBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView3.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.VISIBLE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView3.setVisibility(View.GONE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        arrowBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView4.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    expandableView4.setVisibility(View.VISIBLE);
                    arrowBtn4.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView4.setVisibility(View.GONE);
                    arrowBtn4.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        arrowBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView5.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.VISIBLE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.GONE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        switch (data){

            case "1":
                break;
            case "2":
                MedName.setText(R.string.s1);
                desc.setText(R.string.s2);
                genins.setText(R.string.s3);
                SE1.setText(R.string.s4);
                SE2.setText(R.string.s5);
                SE3.setText(R.string.s6);
                SE4.setText(R.string.s7);
                SE5.setText(R.string.s8);
                SE6.setText(R.string.s9);
                SE7.setText(R.string.s10);
                SE8.setText(R.string.s11);
                SE9.setText(R.string.s12);
                SE10.setText(R.string.s13);
                SE11.setText(R.string.s14);

                break;
            case "3":
                MedName.setText(R.string.p1);
                desc.setText(R.string.p2);
                genins.setText(R.string.p3);
                SE1.setText(R.string.p4);
                SE2.setText(R.string.p5);
                SE3.setText(R.string.p6);
                SE4.setText(R.string.p7);
                SE5.setText(R.string.p8);
                SE6.setText(R.string.p9);
                SE7.setVisibility(View.GONE);
                SE8.setVisibility(View.GONE);
                SE9.setVisibility(View.GONE);
                SE10.setVisibility(View.GONE);
                SE11.setVisibility(View.GONE);
                break;
        }

        return rootview;
    }

}