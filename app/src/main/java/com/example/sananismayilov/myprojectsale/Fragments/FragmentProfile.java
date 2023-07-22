package com.example.sananismayilov.myprojectsale.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.HttpsTrustManager;
import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.İntentAcivity.LoginandregisterActivity;
import com.example.sananismayilov.myprojectsale.İntentAcivity.ProfileOtherItem;
import com.example.sananismayilov.myprojectsale.İntentAcivity.User;
import com.example.sananismayilov.myprojectsale.İntentAcivity.UserOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class FragmentProfile extends Fragment {
    TextView textViewsignout, textViewnameandsurname, textVieworder, textViewinstagram,textViewkampaniya,textViewmaqaza,textViewodenis,textViewdestek,textViewinfo;
    String k = "";
    Intent intent;
    SharedPreferences sharedPreferences;
    SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewsignout = v.findViewById(R.id.profileitem9);
        textViewnameandsurname = v.findViewById(R.id.text);
        textVieworder = v.findViewById(R.id.profileitemorder);
        textViewinstagram = v.findViewById(R.id.profileitem4);
        textViewkampaniya = v.findViewById(R.id.profileitem1);
        textViewmaqaza = v.findViewById(R.id.profileitem2);
        textViewodenis = v.findViewById(R.id.profileitem3);
        textViewdestek = v.findViewById(R.id.profileitem5);
        textViewinfo = v.findViewById(R.id.profileitem6);
        textViewnameandsurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), User.class);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", MODE_PRIVATE);
                String tokens = sharedPreferences.getString("user-token", "");
                intent.putExtra("token", tokens);
                startActivity(intent);
            }
        });

        textViewsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = v.getContext().openOrCreateDatabase("SelectedProducts", Context.MODE_PRIVATE, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setMessage("Hesabınızdan çıxış etmək istəyirsiniz?");
                alertDialog.setTitle("Çıxış");
                alertDialog.setIcon(R.drawable.baseline_logout_24);
                alertDialog.setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.execSQL("DELETE FROM selectedproductsModel");
                        database.execSQL("DELETE FROM selectedproducts");
                        sharedPreferences = getContext().getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user-token", "null");
                        editor.putBoolean("checkboxstatus",false);
                        editor.apply();
                        Intent intent1 = new Intent(getContext(), LoginandregisterActivity.class);
                        startActivity(intent1);
                    }
                }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                });
                alertDialog.show();


            }
        });
        textVieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), UserOrder.class);
                startActivity(intent1);
            }
        });

        textViewinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gottolink("https://www.instagram.com/_i.sanan_");
            }
        });
        textViewinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), ProfileOtherItem.class);
                intent1.putExtra("selecteditem","info");
                startActivity(intent1);
            }
        });

        getuserData();
        return v;
    }


    public void getuserData() {
        HttpsTrustManager.allowAllSSL();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity", MODE_PRIVATE);
        String tokens = sharedPreferences.getString("user-token", "");
        if (!tokens.equals("")) {
            String url = "https://senan2.000webhostapp.com/SaleProject/loginSaleProject/getUser.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("User");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            k = jsonObject.getString("name");
                            k += " ";
                            k += jsonObject.getString("surname");

                        }
                        textViewnameandsurname.setText(k);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "sss", Toast.LENGTH_SHORT).show();
                }
            }) {

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("token", tokens);
                    return map;
                }
            };
            Volley.newRequestQueue(getContext()).add(request);
        }
    }
    public  void gottolink(String link){
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setData(Uri.parse(link));
        startActivity(intent1);

    }

}