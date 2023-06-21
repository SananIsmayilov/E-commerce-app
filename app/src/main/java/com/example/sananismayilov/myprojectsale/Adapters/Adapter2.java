package com.example.sananismayilov.myprojectsale.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.Gorunum2Binding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.adapterview> {

    ArrayList<Conteyner1> conteyner1s;

    public Adapter2(ArrayList<Conteyner1> conteyner1s) {
        this.conteyner1s = conteyner1s;
    }

    @NonNull
    @Override
    public adapterview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Gorunum2Binding binding = Gorunum2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new adapterview(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterview holder, int position) {
        if(conteyner1s.size()>0){
            holder.binding.adfavorits.setText(conteyner1s.get(position).ad);
            holder.binding.modelfavorits.setText(conteyner1s.get(position).model);
            holder.binding.qiymetfavorits.setText(conteyner1s.get(position).qiymet);
            Picasso.get().load(conteyner1s.get(position).qiymet).into(holder.binding.imagefavorits);
        }


    }

    @Override
    public int getItemCount() {
        return conteyner1s.size();
    }

    public class adapterview extends  RecyclerView.ViewHolder{
        Gorunum2Binding binding;
        public adapterview(@NonNull Gorunum2Binding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
