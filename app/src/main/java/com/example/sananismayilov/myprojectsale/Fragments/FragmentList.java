package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class FragmentList extends Fragment {
ArrayAdapter<String> arrayAdapter;
ArrayList<String>CatalogArraylist;
ListView listView;
ProgressBar progressBar;
Animation animation,animation1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        getData();
        listView = view.findViewById(R.id.listview);
        progressBar = view.findViewById(R.id.progress);
       animation =  AnimationUtils.loadAnimation(getContext(),R.anim.animation);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getContext(), CatalogArraylist.get(position), Toast.LENGTH_SHORT).show();
           }
       });
        return view;

    }

    public void getData(){
        CatalogArraylist = new ArrayList<>();
        String url = "https://6474d7397de100807b1bd749.mockapi.io/Catalog";
     StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
             try {
                 JSONArray array = new JSONArray(response);
                 for(int i=0;i<array.length();i++){
                     JSONObject jsonObject = array.getJSONObject(i);
                     String k = jsonObject.getString("catalog");
                     System.out.println("salam"+k);
                     CatalogArraylist.add(k);
                 }
                 progressBar.setAnimation(animation);
                 Collections.sort(CatalogArraylist);
                 if(CatalogArraylist.size()==0){
                     Toast.makeText(getContext(), "Bos", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,CatalogArraylist);
                     listView.setAdapter(arrayAdapter);
                 }


             } catch (JSONException e) {
                 System.out.println("salame"+e.toString());
                 throw new RuntimeException(e);
             }
         }

     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             System.out.println("salam " + error.toString());
         }
     });
        Volley.newRequestQueue(getContext()).add(request);

    }

    @Override
    public void onPause() {
        Volley.newRequestQueue(getContext()).stop();
        super.onPause();
    }
}