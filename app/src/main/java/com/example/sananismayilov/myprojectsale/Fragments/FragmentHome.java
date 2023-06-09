package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.Adapters.Adapter1;
import com.example.sananismayilov.myprojectsale.Adapters.Conteyner;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentHome extends Fragment {
 ArrayList<Conteyner> arrayList;
    Adapter1 adapter1 ;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Animation animation;
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyc1);
        progressBar = view.findViewById(R.id.progressBar);
        imageView = view.findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.animation);
        getData();
        // Inflate the layout for this fragment
        return view ;
    }
    public void getData(){
        arrayList = new ArrayList<>();
        String url = "https://6474d7397de100807b1bd749.mockapi.io/Main";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String picture = object.getString("picture");
                        String name = object.getString("name");
                        String qiymet = object.getString("qiymet");
                        String model = object.getString("model");
                        int id = object.getInt("id");
                        Conteyner conteyner = new Conteyner(picture,name,model,qiymet,id);
                        arrayList.add(conteyner);
                    }
                    if(arrayList.size()==0){
                        progressBar.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                    }else{
                        Adapter1 adapter1 = new Adapter1(arrayList);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setHasFixedSize(true);
                        progressBar.setAnimation(animation);
                        recyclerView.setAdapter(adapter1);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(request);

    }
}