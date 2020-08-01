package com.example.wikipill;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineFragment extends Fragment {

    CardView card1,card2,card3,card4;
    Button but;
    TextView new1,new2;
    FirebaseDatabase database;
    DatabaseReference ref;
    Medicine medicine;

    Button SaradinButton,FexofayButton,ParacipButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        but =view.findViewById(R.id.buttonmarkMedicine);
        new1 = view.findViewById(R.id.name);
        new2 = view.findViewById(R.id.timesaday);
        SaradinButton = view.findViewById(R.id.saradinButton);
        FexofayButton = view.findViewById(R.id.fexofayButton);
        ParacipButton = view.findViewById(R.id.paracipButton);

        this.database = FirebaseDatabase.getInstance();
        this.ref = this.database.getReference("Medicine");
        this.medicine = new Medicine();

        SaradinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            //Diagnosis.this.Count1 = Integer.parseInt(Diagnosis.this.paracipText.getText().toString());

                            medicine.setTaken(1);
                            ref.child("saradon").child("taken").setValue(1);
                        } catch (NullPointerException e) {
                            Log.i(NotificationCompat.CATEGORY_MESSAGE, e.toString());
                        }
                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        final  global abc = (global) getActivity().getApplicationContext();
        if(abc.getVisi()==1){
            Log.i("error","entered");
            card4.setVisibility(View.VISIBLE);
            but.setText("Mark as Taken");
            new2.setText(abc.getNewMedDose());
            new1.setText(abc.getNewMedName());

        }
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MedicineInfo.class);
                intent.putExtra("MedicineName","1");
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MedicineInfo.class);
                intent.putExtra("MedicineName","2");
                startActivity(intent);            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MedicineInfo.class);
                intent.putExtra("MedicineName","3");
                startActivity(intent);            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MedicineInfo.class);
                intent.putExtra("MedicineName","4");
                startActivity(intent);            }
        });



        return  view;
    }
}