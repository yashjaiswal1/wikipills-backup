package com.example.wikipill;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineFragment extends Fragment {

    CardView card1,card2,card3,card4;
    Button but;
    TextView new1,new2;
    FirebaseDatabase database;
    DatabaseReference ref;
    Medicine medicine;
    String Fex,Sara,Para;
    int intFex,intSara,intPara;

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

        final global global = (global) getActivity().getApplicationContext();
        Fex = global.getFex();
        FexofayButton.setText(Fex+"/1");
        Sara = global.getSari();
        SaradinButton.setText(Sara+"/4");
        Para = global.getPara();
        ParacipButton.setText(Para+"/2");
        intFex = Integer.parseInt(Fex);
        intSara = Integer.parseInt(Sara);
        intPara = Integer.parseInt(Para);
        Log.i("value",Integer.toString(intFex) + Integer.toString(intSara) + Integer.toString(intPara));



        this.database = FirebaseDatabase.getInstance();
        this.ref = this.database.getReference("Medicine");
        this.medicine = new Medicine();


        FexofayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFex fexofay = new UpdateFex();
                int b = intFex;
                b = b+1;
                intFex = b;
                Log.i("value",Integer.toString(intFex));
                //Toast.makeText(getActivity().getApplicationContext(),"Tag1",Toast.LENGTH_SHORT).show();

                FexofayButton.setText(Integer.toString(intFex)+"/1");
                fexofay.execute(intFex);

                //Toast.makeText(getActivity().getApplicationContext(),"Tag3",Toast.LENGTH_SHORT).show();

            }
        });

        ParacipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int c = intPara;
                c = c+1;
                intPara = c;
                ParacipButton.setText(Integer.toString(intPara)+"/2");
                UpdatePara paracip = new UpdatePara();
                paracip.execute(intPara);
            }
        });

        SaradinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int d = intSara;
                d = d+1;
                intSara = d;
                SaradinButton.setText(Integer.toString(intSara)+"/4");
                UpdateSara saradin = new UpdateSara();
                saradin.execute(intSara);

//                ref.addValueEventListener(new ValueEventListener() {
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        try {
//                            //Diagnosis.this.Count1 = Integer.parseInt(Diagnosis.this.paracipText.getText().toString());
//
//                            medicine.setTaken(1);
//                            ref.child("saradon").child("taken").setValue(1);
//
//                        } catch (NullPointerException e) {
//                            Log.i(NotificationCompat.CATEGORY_MESSAGE, e.toString());
//                        }
//                    }
//
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                });
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
    private class UpdateFex extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.updateFexofay(integers[0]);
            return null;
        }

    }
    private class UpdatePara extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.updateParacip(integers[0]);
            return null;
        }
    }
    private class UpdateSara extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
            databaseAccess.updatesaradon(integers[0]);
            return null;
        }
    }

}