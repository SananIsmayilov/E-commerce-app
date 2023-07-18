package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.sananismayilov.myprojectsale.Fragments.InfoFragment;
import com.example.sananismayilov.myprojectsale.R;

public class ProfileOtherItem extends AppCompatActivity {
    InfoFragment infofragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_other_item);
        Intent intent = getIntent();
        String receiveitem = intent.getStringExtra("selecteditem");

        if (receiveitem.equals("info")) {
            infofragment = new InfoFragment();
            setFragment(infofragment);
        }
    }

    public void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayoutotheritem, fragment).commit();
    }
}