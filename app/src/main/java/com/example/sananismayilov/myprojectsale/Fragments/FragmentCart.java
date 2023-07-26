package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sananismayilov.myprojectsale.Adapters.AdapterToCartFragment;
import com.example.sananismayilov.myprojectsale.Adapters.Conteyner1;
import com.example.sananismayilov.myprojectsale.Adapters.ConteynerToFragmentCart;
import com.example.sananismayilov.myprojectsale.R;

import java.util.ArrayList;

public class FragmentCart extends Fragment {

    RecyclerView recyclerView;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<ConteynerToFragmentCart> conteynerToFragmentCartArrayList;
    AdapterToCartFragment adapterToCartFragment;
    ImageView imageView;

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
        imageView = view.findViewById(R.id.imageView3);

        getSelectedProducts();
        return view;
    }


    public void getSelectedProducts() {
        conteynerToFragmentCartArrayList = new ArrayList<>();
        sqLiteDatabase = getContext().openOrCreateDatabase("SelectedProducts", Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM selectedproducts", null);
        int indexname = cursor.getColumnIndex("ad");
        int indexmodel = cursor.getColumnIndex("model");
        int indexprice = cursor.getColumnIndex("qiymet");
        int indexpicture = cursor.getColumnIndex("picture");
        while (cursor.moveToNext()) {
            String name = cursor.getString(indexname);
            String model = cursor.getString(indexmodel);
            String price = cursor.getString(indexprice);
            String picture = cursor.getString(indexpicture);
            ConteynerToFragmentCart conteynerToFragmentCart = new ConteynerToFragmentCart(name, model, price, picture);
            conteynerToFragmentCartArrayList.add(conteynerToFragmentCart);
        }
        cursor.close();
        if (conteynerToFragmentCartArrayList.size() == 0){
            imageView.setVisibility(View.VISIBLE);
        }
        adapterToCartFragment = new AdapterToCartFragment(conteynerToFragmentCartArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapterToCartFragment);
        adapterToCartFragment.notifyDataSetChanged();
    }

}