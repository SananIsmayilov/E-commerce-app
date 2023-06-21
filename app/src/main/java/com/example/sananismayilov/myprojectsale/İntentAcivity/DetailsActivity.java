package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sananismayilov.myprojectsale.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textViewad,textViewmodel,textViewsale;
    ImageView imageView;
    Button button;
    Animation animation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_acticity);
        textViewad = findViewById(R.id.detailsname);
        textViewmodel = findViewById(R.id.detailsmodel);
        textViewsale = findViewById(R.id.detailssale);
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.button2);
        animation = AnimationUtils.loadAnimation(this,R.anim.animation2);
        Intent intent = getIntent();
        textViewad.setText(intent.getStringExtra("ad"));
        textViewmodel.setText(intent.getStringExtra("model"));
        textViewsale.setText(intent.getStringExtra("qiymet"));
        button.setAnimation(animation);
        Picasso.get().load(intent.getStringExtra("sekil")).error(R.drawable.bos).into(imageView);

    }
}