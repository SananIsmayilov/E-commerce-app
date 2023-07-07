package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginandregisterActivity extends AppCompatActivity {
   private EditText eemail,ename,essurname,epassword;
    private  SharedPreferences sharedPreferences;
   private SharedPreferences.Editor editor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginandregister);
        eemail = findViewById(R.id.signemail);
        ename = findViewById(R.id.signname);
        epassword = findViewById(R.id.signpassword);
        essurname = findViewById(R.id.signsurname);

        sharedPreferences = this.getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String tokens_sharedPreferences = sharedPreferences.getString("user-token","null");
        String tokens_sharedPreferenceslogin = sharedPreferences.getString("token-user","null");

        if(!tokens_sharedPreferences.equals("null") || !tokens_sharedPreferenceslogin.equals("null") ){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public  void signbtn(View v){
        insertuserinformation();
    }
    public void insertuserinformation(){

        String useremail = eemail.getText().toString().trim();
        String username = ename.getText().toString().trim();
        String usersurname = essurname.getText().toString().trim();
        String userpassword = epassword.getText().toString().trim();
        UUID uuid = UUID.randomUUID();
        String stringtoken = uuid.toString();
        String url = "https://senan2.000webhostapp.com/SaleProject/loginSaleProject/insertuserinformation.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                   int code = object.getInt("Code");
                    if(code == 1){
                        editor.clear();
                        editor.putString("user-token",stringtoken);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                editor.putString("user-token","null");
                editor.apply();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",useremail);
                params.put("password",userpassword);
                params.put("name",username);
                params.put("surname",usersurname);
                params.put("token",stringtoken);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    public void intenttologinpage(View v){
        Intent intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }

}