package com.example.sananismayilov.myprojectsale.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sananismayilov.myprojectsale.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PopupFragment extends Fragment {
    TextView textViewad,textViewmodel,textViewsale;
    ImageView imageView;
    Button button;
    Animation animation;
    Intent intent;
    ProgressBar progressBar;
    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getActivity().getIntent();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popup, container, false);


        textViewad = v.findViewById(R.id.detailsname);
        textViewmodel = v.findViewById(R.id.detailsmodel);
        textViewsale = v.findViewById(R.id.detailssale);
        imageView = v.findViewById(R.id.imageView2);
        button = v.findViewById(R.id.buttonorder);
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.animation2);
        progressBar = v.findViewById(R.id.progressbar1);

        textViewad.setText(intent.getStringExtra("ad"));
        textViewmodel.setText(intent.getStringExtra("model"));
        textViewsale.setText(intent.getStringExtra("qiymet")+ " AZN");
        button.setAnimation(animation);
        Picasso.get().load(intent.getStringExtra("sekil")).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
              progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              preferences = getContext().getSharedPreferences("com.example.sananismayilov.myprojectsale.İntentAcivity",MODE_PRIVATE);
              String tokens = preferences.getString("user-token","");
              if(!tokens.equals("")){
                insertorder(intent.getStringExtra("ad"), intent.getStringExtra("model"),tokens,v);}
            }
        });
        return v ;
    }
    public void insertorder(String name,String model,String token,View v){
        String url = "https://senan2.000webhostapp.com/SaleProject/ProductSaleProject/insertuserorder.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    int code = object.getInt("Code");
                    if(code == 1){
                    Snackbar.make(v,"Sifarişiniz əlavə edildi , təsdiq etmək üçün sifarişlərim bölməsinə daxil olun",Snackbar.LENGTH_SHORT)
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.parseColor("#317ac7"))
                            .show();}
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
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("user_token",token);
                stringMap.put("product_name",name);
                stringMap.put("product_model",model);
                return stringMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
}