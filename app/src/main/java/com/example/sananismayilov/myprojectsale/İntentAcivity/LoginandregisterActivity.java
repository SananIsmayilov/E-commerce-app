package com.example.sananismayilov.myprojectsale.İntentAcivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginandregisterActivity extends AppCompatActivity {
    private EditText eemail, ename, essurname, epassword, ephonenumber;
    private SharedPreferences.Editor editor;
    public NotificationCompat.Builder builder;
    Button button;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginandregister);
        eemail = findViewById(R.id.signemail);
        ename = findViewById(R.id.signname);
        epassword = findViewById(R.id.signpassword);
        essurname = findViewById(R.id.signsurname);
        ephonenumber = findViewById(R.id.signphonenumber);
        button = findViewById(R.id.signbtn);
        progressBar = findViewById(R.id.progressBar2);
        try {
           SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", MODE_PRIVATE);
           editor = sharedPreferences.edit();
            String tokens_sharedPreferences = sharedPreferences.getString("user-token", "null");
            if (!tokens_sharedPreferences.equals("null")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }




    }

    public void signbtn(View v) {
        progressBar.setVisibility(View.VISIBLE);
        insertuserinformation();
    }

    public void insertuserinformation() {

        String useremail = eemail.getText().toString().trim();
        String username = ename.getText().toString().trim();
        String usersurname = essurname.getText().toString().trim();
        String userpassword = epassword.getText().toString().trim();
        String userphonenumber = ephonenumber.getText().toString().trim();
        if (!TextUtils.isEmpty(useremail) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(usersurname) && !TextUtils.isEmpty(userpassword) && !TextUtils.isEmpty(userphonenumber)) {
            editor.clear();
            editor.apply();
            UUID uuid = UUID.randomUUID();
            String stringtoken = uuid.toString();
            String url = "https://senan2.000webhostapp.com/SaleProject/loginSaleProject/insertuserinformation.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        int code = object.getInt("Code");
                        if (code == 1) {
                            editor.clear();
                            editor.putString("user-token", stringtoken);
                            editor.apply();
                            shownotification(username);
                            button.setClickable(false);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    editor.putString("user-token", "null");
                    editor.apply();
                    Toast.makeText(LoginandregisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    button.setClickable(true);
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", useremail);
                    params.put("password", userpassword);
                    params.put("name", username);
                    params.put("surname", usersurname);
                    params.put("token", stringtoken);
                    params.put("phone_number", userphonenumber);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(request);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Gözlənilməyən xəta baş verdi", Toast.LENGTH_SHORT).show();
        }

    }

    public void intenttologinpage(View v) {
        Intent intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onBackPressed() {
        editor.putString("user-token", "null");
        editor.apply();
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
    }

    public void shownotification(String name) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelid = "channelid";
            String channelname = "channelname";
            String channeldescription = "channeldescription";
            int channelimportantly = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = manager.getNotificationChannel(channelid);

            if (channel == null) {
                channel = new NotificationChannel(channelid, channelname, channelimportantly);
                channel.setDescription(channeldescription);
                manager.createNotificationChannel(channel);
            }
            builder = new NotificationCompat.Builder(this, channelid);
            builder.setContentTitle("Hörmətli "+ name);
            builder.setContentText("Tətbiqə xoş gəlmisiniz");
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.icontontfc);
            builder.setContentIntent(pendingIntent);

        } else {
            builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("Hörmətli "+ name);
            builder.setContentText("Tətbiqə xoş gəlmisiniz");
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.icontontfc);
            builder.setPriority(Notification.PRIORITY_HIGH);
            builder.setContentIntent(pendingIntent);
        }
        manager.notify(1, builder.build());
    }
}