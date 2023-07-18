package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sananismayilov.myprojectsale.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!checkInternetConnection()){
            Toast.makeText(this, "Internet bağlantınızı yoxlayın", Toast.LENGTH_SHORT).show();
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.statusbarcolor));
        NavigationComponentInitialize();



    }

    public void NavigationComponentInitialize(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
    }
    public boolean checkInternetConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        } return networkInfo.isConnected();

    }

}