package com.example.wikipill;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiagnosisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiagnosisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiagnosisFragment newInstance(String param1, String param2) {
        DiagnosisFragment fragment = new DiagnosisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        viewPagerAdapter.addFragment(medicineFragment, "MONDAY");
        viewPagerAdapter.addFragment(medicineFragment2, "TUESDAY");
        viewPagerAdapter.addFragment(medicineFragment3, "WEDNESDAY");
        viewPagerAdapter.addFragment(medicineFragment4, "THURSDAY");
        viewPagerAdapter.addFragment(medicineFragment5,"FRIDAY");
        viewPagerAdapter.addFragment(medicineFragment6,"SATUARDAY");
        viewPagerAdapter.addFragment(medicineFragment7,"SUNDAY");
        viewPager.setAdapter(viewPagerAdapter);


        final global abc = (global) getActivity().getApplicationContext();


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

}