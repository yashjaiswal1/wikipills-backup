package com.example.wikipill;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiagnosisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiagnosisFragment extends Fragment {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    FloatingActionButton add;
    MedicineFragment2 medicineFragment2;
    MedicineFragment medicineFragment;
    MedicineFragment3 medicineFragment3;
    MedicineFragment4 medicineFragment4;
    MedicineFragment5 medicineFragment5;
    MedicineFragment6 medicineFragment6;
    MedicineFragment7 medicineFragment7;
    EditText Name,dose,time;

    String sSavedLocale = "en";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final  global abc = (global) getActivity().getApplicationContext();


        // Inflate the layout for this fragment
         final View view = inflater.inflate(R.layout.fragment_diagnosis, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        add = view.findViewById(R.id.add_medicine_fab);

        Name = view.findViewById(R.id.medicine_name);
        dose = view.findViewById(R.id.dose);
        time = view.findViewById(R.id.time);

        medicineFragment2 = new MedicineFragment2();
        medicineFragment = new MedicineFragment();
        medicineFragment3 = new MedicineFragment3();
        medicineFragment4 = new MedicineFragment4();
        medicineFragment5 = new MedicineFragment5();
        medicineFragment6 = new MedicineFragment6();
        medicineFragment7 = new MedicineFragment7();

        tabLayout.setupWithViewPager(viewPager);

        DiagnosisFragment.ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(),0);

        //Diagnosis.ViewPagerAdapter viewPagerAdapter = new Diagnosis.ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(medicineFragment, getString(R.string.monday));
        viewPagerAdapter.addFragment(medicineFragment2, getString(R.string.tuesday));
        viewPagerAdapter.addFragment(medicineFragment3, getString(R.string.wednesday));
        viewPagerAdapter.addFragment(medicineFragment4, getString(R.string.thursday));
        viewPagerAdapter.addFragment(medicineFragment5,getString(R.string.friday));
        viewPagerAdapter.addFragment(medicineFragment6,getString(R.string.saturday));
        viewPagerAdapter.addFragment(medicineFragment7,getString(R.string.sunday));
        viewPager.setAdapter(viewPagerAdapter);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(
                        getActivity(),R.style.BottomSheetDialogTheme
                );
                final View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.bottom_sheet_medicine,
                                (RelativeLayout)view.findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Name = bottomSheetView.findViewById(R.id.medicine_name);
                        dose = bottomSheetView.findViewById(R.id.dose);
                        time = bottomSheetView.findViewById(R.id.time);

                        abc.setNewMedDose(dose.getText().toString());
                        abc.setNewMedName(Name.getText().toString());
                        abc.setNewMedTime(time.getText().toString());
                        abc.setVisi(1);

                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });




         return view;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    private void setAppLocale (String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        }else {
            config.locale=new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(config,dm);
        //setContentView(R.layout.activity_main);
        getActivity().recreate();
    }



}