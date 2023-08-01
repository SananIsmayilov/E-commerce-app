package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
private EditText loginemail,loginpassword;
private SharedPreferences sharedPreferences;
private  SharedPreferences.Editor editor;
ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        progressBar = findViewById(R.id.progressBar3);

        sharedPreferences = this.getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void loginbtn(View v){
        progressBar.setVisibility(View.VISIBLE);
      String loginemails = loginemail.getText().toString().trim();
      String loginpasswords = loginpassword.getText().toString().trim();
      if(!TextUtils.isEmpty(loginemails) && !TextUtils.isEmpty(loginpasswords)) {

          String url = "https://senan2.000webhostapp.com/SaleProject/loginSaleProject/Login.php";

          StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  try {
                      JSONObject jsonObject = new JSONObject(response);
                      int code = jsonObject.getInt("code");

                      if (code == 1) {
                          String token = jsonObject.getString("token");
                          editor.putString("user-token", token);
                          editor.apply();
                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                          progressBar.setVisibility(View.INVISIBLE);
                          startActivity(intent);
                          finish();
                      } else if (code == 0) {
                          Toast.makeText(LoginActivity.this, "Wrong password or email", Toast.LENGTH_SHORT).show();
                      }


                  } catch (JSONException e) {
                      Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                      System.out.println("ERROR" + e.toString());
                      throw new RuntimeException(e);
                  }

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
              }
          }) {
              @Nullable
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> maps = new HashMap<>();
                  maps.put("email", loginemails);
                  maps.put("password", loginpasswords);
                  return maps;
              }
          };

          Volley.newRequestQueue(this).add(request);
      }else {
          Toast.makeText(this, "No email or password", Toast.LENGTH_SHORT).show();
      }
    }
}