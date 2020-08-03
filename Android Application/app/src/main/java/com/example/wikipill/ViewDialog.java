package com.example.wikipill;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ViewDialog {

    MediaPlayer mediaPlayer;

    public void showDialog(Activity activity, String msg,int i)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.notification_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            int RESID = R.raw.alert;
        if(mediaPlayer!=null) {
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        if (i==1) {
             RESID = R.raw.weather_hindi;
        }else if (i==2){
             RESID = R.raw.geofencing_eng;
        } else if (i==3)
        {
            RESID = R.raw.credits;
        }else  if (i==4){
            RESID = R.raw.credits_eng;
        }else if (i==5){
            RESID = R.raw.weather_eng;
        }
         mediaPlayer = MediaPlayer.create(activity,RESID);
        mediaPlayer.start();

        TextView text =  dialog.findViewById(R.id.text);
        text.setText(msg);

        Button dialogBtn_cancel =  dialog.findViewById(R.id.dismiss);
        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
