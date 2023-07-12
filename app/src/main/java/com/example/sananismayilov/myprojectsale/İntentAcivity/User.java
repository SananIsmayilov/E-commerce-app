package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.ActivityUserBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User extends AppCompatActivity {
Intent intent;
ActivityUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        intent = getIntent();
        getuserData();

    }



    public void getuserData(){
        String tokens = intent.getStringExtra("token");
        if(!tokens.equals("")){
            String url = "https://senan2.000webhostapp.com/SaleProject/loginSaleProject/getUser.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("User");
                        for (int i = 0;i<array.length();i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            binding.username.setText(jsonObject.getString("name"));
                            binding.usersurname.setText(jsonObject.getString("surname"));
                            binding.usermail.setText(jsonObject.getString("email"));
                            binding.userpassword.setText(jsonObject.getString("password"));
                        }



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("token",tokens);
                    return map;
                }
            };
            Volley.newRequestQueue(this).add(request);
        }



    }
}