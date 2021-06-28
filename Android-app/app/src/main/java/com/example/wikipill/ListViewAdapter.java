package com.example.wikipill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;
    FragmentManager fragmentManager;

    //constructor
    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView mTitleTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);

            holder.mIconIv = view.findViewById(R.id.mainIcon);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //set the results into textviews
        holder.mTitleTv.setText(modellist.get(postition).getTitle());

        //set the result in imageview
        holder.mIconIv.setImageResource(modellist.get(postition).getIcon());

        //listview item clicks
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //code later
                Log.i("New Activitty",modellist.get(postition).getTitle().toString());
                if (modellist.get(postition).getTitle().equals("Fexofenadine")) {
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Battery");
                    //intent.putExtra("contentTv", "This is Battery detail...");
                    //mContext.startActivity(intent);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", "1");
                    Fragment fragment = null;
                    fragment = new LogFragment();
                    fragment.setArguments(bundle);
                    fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container_frag,fragment).commit();
                    Log.i("New Activitty","Amoxycillin");
                }
                if (modellist.get(postition).getTitle().equals("Saradin")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Montelukast");
                    //intent.putExtra("contentTv", "This is Cpu detail...");
                    //mContext.startActivity(intent);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", "2");
                    Fragment fragment = null;
                    fragment = new LogFragment();
                    fragment.setArguments(bundle);
                    fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container_frag,fragment).commit();
                    Log.i("New Activitty","Montelukast");
                }
                if (modellist.get(postition).getTitle().equals("Paracip")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Ambrodil");
                    //intent.putExtra("contentTv", "This is Display detail...");
                   // mContext.startActivity(intent);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", "3");
                    Fragment fragment = null;
                    fragment = new LogFragment();
                    fragment.setArguments(bundle);
                    fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container_frag,fragment).commit();
                    Log.i("New Activitty","Ambrodil");
                }
                /*if (modellist.get(postition).getTitle().equals("Antihistiminics")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Antihistiminics");
                    //intent.putExtra("contentTv", "This is Memory detail...");
                    //mContext.startActivity(intent);
                    Log.i("New Activitty","Antihistiminics");
                }
                if (modellist.get(postition).getTitle().equals("Paracetamol")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Paracetamol");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                   // mContext.startActivity(intent);
                    Log.i("New Activitty","Paracetamol");
                }
                if (modellist.get(postition).getTitle().equals("Declofenac")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Declofenac");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                    //mContext.startActivity(intent);
                    Log.i("New Activitty","Declofenac");
                }
                if (modellist.get(postition).getTitle().equals("Ibuprofen")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Ibuprofen");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                    //mContext.startActivity(intent);
                    Log.i("New Activitty","Ibuprofen");
                }
                if (modellist.get(postition).getTitle().equals("Levocetrizine")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Levocetrizine");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                    //Context.startActivity(intent);
                    Log.i("New Activitty","Levocetrizine");
                }
                if (modellist.get(postition).getTitle().equals("Disprin")){
                    //start NewActivity with title for actionbar and text for textview
                    //Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Disprin");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                    //mContext.startActivity(intent);
                    Log.i("New Activitty","Disprin");
                }
                if (modellist.get(postition).getTitle().equals("Ecosprin")){
                    //start NewActivity with title for actionbar and text for textview
                    ////Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Ecosprin");
                    //intent.putExtra("contentTv", "This is Sensor detail...");
                    //mContext.startActivity(intent);
                    Log.i("New Activitty","Ecosprin");
                }*/
            }
        });


        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
