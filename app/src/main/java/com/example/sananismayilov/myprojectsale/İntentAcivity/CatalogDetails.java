package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.Adapters.AdapterToCatalogDetails;
import com.example.sananismayilov.myprojectsale.Adapters.ConteynerToCatalogDetails;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatalogDetails extends AppCompatActivity {
    ArrayList<ConteynerToCatalogDetails> catalogDetails;
    AdapterToCatalogDetails adapterToCatalogDetails;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_details);

        getWindow().setStatusBarColor(ContextCompat.getColor(CatalogDetails.this,R.color.statusbarcolor));
        Intent intent = getIntent();
        String k = intent.getStringExtra("sentCatalog");

        recyclerView = findViewById(R.id.recyclerviewforcatalogdetails);
        getSelectedcatalog(k);
    }
    public void getSelectedcatalog(String catalog_name){
        catalogDetails = new ArrayList<>();

        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/getselecteddata.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("Products");
                    for (int i= 0; i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name_catalog = jsonObject.getString("product_name");
                        String model_catalog = jsonObject.getString("product_model");
                        String price_catalog = jsonObject.getString("product_price");
                        String picture_catalog = jsonObject.getString("product_picture");
                        ConteynerToCatalogDetails conteyner = new ConteynerToCatalogDetails(name_catalog,model_catalog,price_catalog,picture_catalog);
                        catalogDetails.add(conteyner);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                adapterToCatalogDetails = new AdapterToCatalogDetails(catalogDetails);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recyclerView.setAdapter(adapterToCatalogDetails);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("catalog_name",catalog_name);
                return stringMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}