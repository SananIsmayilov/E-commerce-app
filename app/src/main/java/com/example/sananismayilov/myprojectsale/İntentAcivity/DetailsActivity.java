package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sananismayilov.myprojectsale.Fragments.PopupFragment;
import com.example.sananismayilov.myprojectsale.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

   PopupFragment popupFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_acticity);

        popupFragment = new PopupFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout,popupFragment).commit();


    }
}