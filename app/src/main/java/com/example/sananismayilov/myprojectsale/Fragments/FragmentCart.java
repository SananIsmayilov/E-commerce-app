package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sananismayilov.myprojectsale.Adapters.Adapter2;
import com.example.sananismayilov.myprojectsale.Adapters.Conteyner1;
import com.example.sananismayilov.myprojectsale.R;

import java.util.ArrayList;

public class FragmentCart extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recylerviewcart);


        // Inflate the layout for this fragment
        return view ;
    }

}