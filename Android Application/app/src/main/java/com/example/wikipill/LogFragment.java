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
                MedName.setText("Sandin 100 mg");
                desc.setText("Sandin 100 mg Tablet is a broad-spectrum antiparasitic medicine used in the prevention and treatment of parasitic infections caused by tapeworms, pinworms, and hookworms. It is particularly effective in treating parasitic infections of the stomach and the intestine.\n");
                genins.setText("Take this medicine exactly as instructed by the doctor. This medicine can be taken with or without food. Do not take in larger or smaller quantities than recommended. Take the medicine for the entire prescribed duration. Ensure that proper hygiene measures are adopted to prevent the recurrence and spread of the infection.\n");
                SE1.setText("Diarrhea");
                SE2.setText("Nausea and Vomiting");
                SE3.setText("Stomach discomfort and pain (RARE)");
                SE4.setText("Loss of appetite");
                SE5.setText("Difficulty in breathing (SEVERE)");
                SE6.setText("Fever with chills");
                SE7.setText("Difficulty in swallowing");
                SE8.setText("Peeling and blistering of skin (RARE|SEVERE)");
                SE9.setText("Hair loss or thinning of the hair (RARE)");
                SE10.setText("Joint or muscle pain");
                SE11.setText("Weight loss");

                break;
            case "3":
                MedName.setText("Paracip 100 MG ");
                desc.setText("Paracip 100 MG Tablet is used to temporarily relieve fever and mild to moderate pain such as muscle ache, headache, toothache, arthritis, and backache. This medicine should be used with caution in patients with liver diseases due to the increased risk of severe adverse effects.\n");
                genins.setText("Take this medicine as advised by your doctor. You may take this medicine with or without food. Do not take in larger amounts than advised. Usage should never exceed the recommended daily maximum dosage limit of 4 grams in a 24-hour time period.\n");
                SE1.setText("Nausea or Vomiting");
                SE2.setText("Allergic skin reaction");
                SE3.setText("Gastric / Mouth Ulcer");
                SE4.setText("Anemia (RARE)");
                SE5.setText("Fatigue");
                SE6.setText("Unusual bleeding or bruising");
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