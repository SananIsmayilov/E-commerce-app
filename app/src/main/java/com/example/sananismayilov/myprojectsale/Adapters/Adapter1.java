package com.example.sananismayilov.myprojectsale.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.İntentAcivity.DetailsActivity;
import com.example.sananismayilov.myprojectsale.Fragments.FragmentHomeDirections;
import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.CardviewgorunumBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.viewholder> {
ArrayList<Conteyner> arrayList;
    boolean b = false;

    public Adapter1(ArrayList<Conteyner> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardviewgorunumBinding binding = CardviewgorunumBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.textViewnamecard.setText(arrayList.get(position).ad);
        holder.binding.textViewmodelcard.setText(arrayList.get(position).model);
        holder.binding.textViewsalecard.setText(arrayList.get(position).qiymet);
        Picasso.get().load(arrayList.get(position).picture).into(holder.binding.imageviewcard);
        holder.binding.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b) {
                    holder.binding.heart.setBackgroundResource(R.drawable.redheart);
                    Snackbar.make(v, "Səbətə əlavə edildi!", Snackbar.LENGTH_SHORT)
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.RED)
                            .setAction("Səbətə daxil olun", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Navigation.findNavController(holder.binding.imageviewcard).navigate(R.id.fragmentCart);
                                }
                            })
                            .show();
                    b = true;
                } else {
                    holder.binding.heart.setBackgroundResource(R.drawable.cart);
                    b = false;
                }

            }
        });
        holder.binding.imageviewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("ad",arrayList.get(position).ad);
                intent.putExtra("model",arrayList.get(position).model);
                intent.putExtra("qiymet",arrayList.get(position).qiymet);
                intent.putExtra("sekil",arrayList.get(position).picture);
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
