package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.sananismayilov.myprojectsale.R;

public class OrderDetailsFragment extends Fragment {

CheckBox checkBox;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_order_details, container, false);

        checkBox = v.findViewById(R.id.checkbox1);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    editor.putBoolean("checkboxstatus",true).apply();
                }
            }
        });
        // Inflate the layout for this fragment
        return v ;
    }
}