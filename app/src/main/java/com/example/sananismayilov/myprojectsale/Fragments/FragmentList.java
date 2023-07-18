package com.example.sananismayilov.myprojectsale.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.sananismayilov.myprojectsale.HttpsTrustManager;
import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.İntentAcivity.CatalogDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class FragmentList extends Fragment {
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> CatalogArraylist;
    ListView listView;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listview);
        progressBar = view.findViewById(R.id.progress);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CatalogDetails.class);
                intent.putExtra("sentCatalog", CatalogArraylist.get(position));
                startActivity(intent);
            }
        });
        getData();
        return view;

    }

    public void getData() {
        CatalogArraylist = new ArrayList<>();
        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/getAllData.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("Products");
                    for (int i = 0; i < array.length(); i++) {
                        boolean b = false;
                        JSONObject jsonObject = array.getJSONObject(i);
                        String k = jsonObject.getString("product_name");
                        for (String f : CatalogArraylist) {
                            if (f.equals(k)) {
                                b = true;
                            }
                        }
                        if (!b) {
                            CatalogArraylist.add(k);
                        }
                    }
                    Collections.sort(CatalogArraylist);
                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, CatalogArraylist);
                    progressBar.setVisibility(View.INVISIBLE);
                    listView.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(request);

    }

}