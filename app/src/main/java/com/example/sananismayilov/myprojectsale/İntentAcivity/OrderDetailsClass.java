package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sananismayilov.myprojectsale.Fragments.OrderDetailsFragment;
import com.example.sananismayilov.myprojectsale.R;

public class OrderDetailsClass extends AppCompatActivity {
OrderDetailsFragment orderDetailsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_class);
        orderDetailsFragment = new OrderDetailsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.detailorderframe,orderDetailsFragment).commit();

    }
}