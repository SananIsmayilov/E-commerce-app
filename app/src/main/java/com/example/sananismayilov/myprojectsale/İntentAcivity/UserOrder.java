package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.Adapters.AdapterToOrders;
import com.example.sananismayilov.myprojectsale.Adapters.ConteynerToOrders;

import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ConteynerToOrders> ordersArrayList;
    SharedPreferences preferences;
    AdapterToOrders adapterToOrders;
    boolean statustocheckbox ;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        recyclerView = findViewById(R.id.recylerviewtoorders);
        preferences = this.getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", MODE_PRIVATE);
        statustocheckbox = preferences.getBoolean("checkboxstatus", false);
        imageView = findViewById(R.id.imageVieworder);
        getOrders();
        if (!statustocheckbox) {
            Intent intent = new Intent(getApplicationContext(), OrderDetailsClass.class);
            startActivity(intent);
        }


    }

    public void getOrders() {
        ordersArrayList = new ArrayList<>();

        String token = preferences.getString("user-token", "");

        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/getselectorder.php";
        if (!token.equals("")) {
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("Products");
                        if (array.length() > 0) {
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object1 = array.getJSONObject(i);
                                String productname = object1.getString("product_name");
                                String productmodel = object1.getString("product_model");
                                String status = object1.getString("status");
                                String productpicture = object1.getString("product_picture");
                                ConteynerToOrders conteyner = new ConteynerToOrders(productname, productmodel, status, productpicture);
                                ordersArrayList.add(conteyner);
                            }

                        }else {
                            imageView.setVisibility(View.VISIBLE);
                        }
                        adapterToOrders = new AdapterToOrders(ordersArrayList);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(UserOrder.this));
                        recyclerView.setAdapter(adapterToOrders);
                    } catch (JSONException e) {
                        Toast.makeText(UserOrder.this, e.toString(), Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UserOrder.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put("token", token);
                    return stringMap;
                }
            };
            Volley.newRequestQueue(this).add(request);

        }
    }
}