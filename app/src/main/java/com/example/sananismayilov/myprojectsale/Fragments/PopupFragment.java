package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sananismayilov.myprojectsale.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class PopupFragment extends Fragment {
    TextView textViewad,textViewmodel,textViewsale;
    ImageView imageView;
    Button button;
    Animation animation;
    Intent intent;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popup, container, false);


        textViewad = v.findViewById(R.id.detailsname);
        textViewmodel = v.findViewById(R.id.detailsmodel);
        textViewsale = v.findViewById(R.id.detailssale);
        imageView = v.findViewById(R.id.imageView2);
        button = v.findViewById(R.id.button2);
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.animation2);
        progressBar = v.findViewById(R.id.progressbar1);

        textViewad.setText(intent.getStringExtra("ad"));
        textViewmodel.setText(intent.getStringExtra("model"));
        textViewsale.setText(intent.getStringExtra("qiymet")+ " AZN");
        button.setAnimation(animation);
        Picasso.get().load(intent.getStringExtra("sekil")).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
              progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return v ;
    }
}