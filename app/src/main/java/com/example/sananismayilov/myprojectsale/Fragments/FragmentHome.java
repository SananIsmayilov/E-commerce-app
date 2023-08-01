package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.Adapters.Adapter1;
import com.example.sananismayilov.myprojectsale.Adapters.Conteyner;
import com.example.sananismayilov.myprojectsale.HttpsTrustManager;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FragmentHome extends Fragment implements SearchView.OnQueryTextListener {
    ArrayList<Conteyner> arrayList;
    Adapter1 adapter1;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Animation animation;
    ImageView imageView;
    TextView textView;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyc1);
        progressBar = view.findViewById(R.id.progressBar);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("");

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.animation);

        getData();
        // Inflate the layout for this fragment
        return view;
    }

    public void getData() {
        HttpsTrustManager.allowAllSSL();
        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/getAllData.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList = new ArrayList<>();

                System.out.println("Resspons" + response.toString());
                try {
                    JSONObject object1 = new JSONObject(response);
                    JSONArray jsonArray = object1.getJSONArray("Products");
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String picture = object.getString("product_picture");
                            String name = object.getString("product_name");
                            String qiymet = object.getString("product_price");
                            String model = object.getString("product_model");
                            int id = object.getInt("product_id");
                            Conteyner conteyner = new Conteyner(picture, name, model, qiymet, id);
                            arrayList.add(conteyner);
                        }
                        if (arrayList.size() == 0) {
                            progressBar.setVisibility(View.INVISIBLE);
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            Adapter1 adapter1 = new Adapter1(arrayList);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            recyclerView.setHasFixedSize(true);
                            progressBar.setAnimation(animation);
                            recyclerView.setAdapter(adapter1);

                            adapter1.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errorerror " + error.toString());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
        Volley.newRequestQueue(getContext()).add(request);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        arrayList.clear();
        getSearchData(newText);
        return true;
    }


    public void getSearchData(final String searchitem) {


        HttpsTrustManager.allowAllSSL();
        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/getSearchData.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList = new ArrayList<>();
                System.out.println("Resspons" + response.toString());
                try {
                    JSONObject object1 = new JSONObject(response);
                    JSONArray jsonArray = object1.getJSONArray("Products");
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String picture = object.getString("product_picture");
                            String name = object.getString("product_name");
                            String qiymet = object.getString("product_price");
                            String model = object.getString("product_model");
                            int id = object.getInt("product_id");
                            Conteyner conteyner = new Conteyner(picture, name, model, qiymet, id);
                            arrayList.add(conteyner);
                        }
                        if (arrayList.size() == 0) {
                            progressBar.setVisibility(View.INVISIBLE);
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            Adapter1 adapter1 = new Adapter1(arrayList);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            recyclerView.setHasFixedSize(true);
                            progressBar.setAnimation(animation);
                            recyclerView.setAdapter(adapter1);

                            adapter1.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errorerror " + error.toString());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("Resspons" + error.toString());
            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("product_name", searchitem);
                return map;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }

}