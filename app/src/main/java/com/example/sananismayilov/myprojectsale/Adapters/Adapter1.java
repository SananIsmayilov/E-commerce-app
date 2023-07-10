package com.example.sananismayilov.myprojectsale.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.İntentAcivity.DetailsActivity;
import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.CardviewgorunumBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.viewholder> {
    ArrayList<Conteyner> arrayList;

    SQLiteDatabase sqLiteDatabase;
    private boolean b;

    public Adapter1(ArrayList<Conteyner> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardviewgorunumBinding binding = CardviewgorunumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.textViewnamecard.setText(arrayList.get(position).ad);
        holder.binding.textViewmodelcard.setText(arrayList.get(position).model);
        holder.binding.textViewsalecard.setText(arrayList.get(position).qiymet + " AZN");
        Picasso.get().load(arrayList.get(position).picture).into(holder.binding.imageviewcard);
        sqLiteDatabase = holder.binding.getRoot().getContext().openOrCreateDatabase("SelectedProducts", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS selectedproducts (ad VARCHAR(255),model VARCHAR(255) , qiymet VARCHAR(255) , picture VARCHAR(255))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS selectedproductsModel (model VARCHAR(255))");

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM selectedproductsModel WHERE model = ?", new String[]{arrayList.get(position).model});
        if (cursor.getCount() > 0) {
            holder.binding.heart.setBackgroundResource(R.drawable.redheart);
            b = true;
        } else {
            holder.binding.heart.setBackgroundResource(R.drawable.cart);
            b = false;
        }
        cursor.close();


        holder.binding.heart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (!b) {
                    holder.binding.heart.setBackgroundResource(R.drawable.redheart);

                    String query1 = "INSERT INTO selectedproductsModel (model) VALUES (?)";
                    SQLiteStatement statement1 = sqLiteDatabase.compileStatement(query1);
                    statement1.bindString(1, arrayList.get(position).model);
                    statement1.execute();

                    String query = "INSERT INTO selectedproducts (ad, model, qiymet, picture) VALUES (?, ?, ?, ?)";
                    SQLiteStatement statement = sqLiteDatabase.compileStatement(query);
                    statement.bindString(1, arrayList.get(position).ad);
                    statement.bindString(2, arrayList.get(position).model);
                    statement.bindString(3, arrayList.get(position).qiymet);
                    statement.bindString(4, arrayList.get(position).picture);
                    statement.execute();

                    Snackbar.make(v, "Məhsul seçildi!", Snackbar.LENGTH_SHORT)
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.parseColor("#317ac7"))
                            .setAction("Səbətə daxil olun", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Navigation.findNavController(holder.binding.imageviewcard).navigate(R.id.fragmentCart);
                                }
                            })
                            .setActionTextColor(Color.RED)
                            .show();

                    b = true;
                } else {
                    SQLiteStatement statement = sqLiteDatabase.compileStatement("DELETE FROM selectedproductsModel WHERE model = ?");
                    statement.bindString(1, arrayList.get(position).model);
                    statement.execute();

                    SQLiteStatement statement1 = sqLiteDatabase.compileStatement("DELETE FROM selectedproducts WHERE model = ?");
                    statement1.bindString(1, arrayList.get(position).model);
                    statement1.execute();

                    holder.binding.heart.setBackgroundResource(R.drawable.cart);

                    Snackbar.make(v, "Seçdiyiniz məhsul seçilmiş məhsulların siyahısından çıxarıldı", Snackbar.LENGTH_SHORT)
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.parseColor("#317ac7"))
                            .show();

                    b = false;
                }
            }
        });

        holder.binding.imageviewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("ad", arrayList.get(position).ad);
                intent.putExtra("model", arrayList.get(position).model);
                intent.putExtra("qiymet", arrayList.get(position).qiymet);
                intent.putExtra("sekil", arrayList.get(position).picture);
                v.getContext().startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CardviewgorunumBinding binding;

        public viewholder(@NonNull CardviewgorunumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}