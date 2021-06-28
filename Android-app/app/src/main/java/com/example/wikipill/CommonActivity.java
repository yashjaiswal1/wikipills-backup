package com.example.wikipill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

public class CommonActivity extends LocalizationActivity {

    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    String s ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        frameLayout = findViewById(R.id.container_frag);

        Intent intent = getIntent();
        s = intent.getStringExtra("Fragment");

        Fragment fragment = null;

        assert s != null;
        switch (s){

            case "1":
                fragment = new DiagnosisFragment();

                break;
            case "2":
                fragment = new CaseHistoryFragment();

                break;
            case "3":
                fragment = new MedicineLogFragment();
                break;
            case "4":
                fragment = new VitalsFragment();
                break;



        }

        if (fragment!=null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container_frag, fragment).commit();

        }

        final global abc  = (global) getApplicationContext();

        if(abc.getLanguage()==1){
            setLanguage("en");
        } else if(abc.getLanguage()==2){

        }else if(abc.getLanguage()==3){
            setLanguage("hn");
        }else if (abc.getLanguage()==4){
            setLanguage("kn");
        }

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
    }
}