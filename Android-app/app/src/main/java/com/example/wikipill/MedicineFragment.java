package com.example.wikipill;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyPresigningClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechPresignRequest;
import com.amazonaws.services.polly.model.Voice;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MedicineFragment extends Fragment {

    CardView card1,card2,card3,card4;
    Button but;
    AmazonPollyPresigningClient client;
    List<Voice> voices;
    TextView new1,new2;
    FirebaseDatabase database;
    DatabaseReference ref;
    Medicine medicine;
    String voiceid;
    private  MediaPlayer mediaPlayer;
    String Fex,Sara,Para;
    int intFex,intSara,intPara;
    private static final String COGNITO_POOL_ID = "us-east-1:634afb0a-1c2c-4875-a73d-3f8a93397a85";
    private static final Regions MY_REGION = Regions.US_EAST_1;

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

        //Amazon Polly
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getActivity(),
                COGNITO_POOL_ID,
                MY_REGION
        );

// Create a client that supports generation of presigned URLs.
        client = new AmazonPollyPresigningClient(credentialsProvider);

        // Create describe voices request.
//        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();



// Synchronously ask Amazon Polly to describe available TTS voices.
//        DescribeVoicesResult describeVoicesResult = client.describeVoices(describeVoicesRequest);
//        List<Voice> voices = describeVoicesResult.getVoices();

        VOICES voice = new VOICES();
        voice.execute();

        SynthesizeSpeechPresignRequest synthesizeSpeechPresignRequest =
                new SynthesizeSpeechPresignRequest()
                        // Set the text to synthesize.
                        .withText("Hello world!")
                        // Select voice for synthesis.
                        .withVoiceId(voiceid) // "Joanna"
                        // Set format to MP3.
                        .withOutputFormat(OutputFormat.Mp3);

// Get the presigned URL for synthesized speech audio stream.

        final URL  presignedSynthesizeSpeechUrl = client.getPresignedSynthesizeSpeechUrl(synthesizeSpeechPresignRequest);



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
                int i = global.getLanguage();
                int RSID;
                int d = 3;
                String pass;
                if (i==3){
                    pass = "आशा है आप जल्दी ठीक होंगे";
                    d = 3;
                    RSID=R.raw.credits;
                }else {
                    pass = "Hope you get well soon.";
                    d = 4;
                    RSID=R.raw.credits_eng;

                }


                ViewDialog viewDialog = new ViewDialog();

                viewDialog.showDialog(getActivity(),pass,d);

//
//                MediaPlayer mediaPlayer = new MediaPlayer();
//
//
//
//
//                mediaPlayer.setAudioAttributes(
//                        new AudioAttributes
//                                .Builder()
//                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                                .build());
//
//
//
//                try {
//                    // Set media player's data source to previously obtained URL.
//                    mediaPlayer.setDataSource(presignedSynthesizeSpeechUrl.toString());
//                } catch (IOException e) {
//                    Log.e("TAG", "Unable to set data source for the media player! " + e.getMessage());
//                }
//
//// Prepare the MediaPlayer asynchronously (since the data source is a network stream).
//                mediaPlayer.prepareAsync();
//
//// Set the callback to start the MediaPlayer when it's prepared.
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mp.start();
//                    }
//                });
//
//// Set the callback to release the MediaPlayer after playback is completed.
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                    }
//                });







//                ViewDialog viewDialog = new ViewDialog();
//
//                    viewDialog.showDialog(getActivity(),pass,d);

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
    private class VOICES extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (voices == null) {

                DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
                DescribeVoicesResult describeVoicesResult;
                try {
                    // Synchronously ask the Polly Service to describe available TTS voices.
                    describeVoicesResult = client.describeVoices(describeVoicesRequest);
                } catch (RuntimeException e) {
                    Log.e("TAG", "Unable to get available voices. " + e.getMessage());
                    return null;
                }

                // Get list of voices from the result.
                voices = describeVoicesResult.getVoices();

                // Log a message with a list of available TTS voices.
                Log.i("TAG", "Available Polly voices: " + voices);
            }

            return null;
        }

        protected void onPostExecute(Void aVoid) {
            if (voices != null) {
                    voiceid = voices.get(0).getId().toString();
                    Log.i("TAG",voiceid);
            }
        }

    }

}