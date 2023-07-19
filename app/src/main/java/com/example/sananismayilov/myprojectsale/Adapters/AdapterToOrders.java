package com.example.sananismayilov.myprojectsale.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.ViewforordersBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterToOrders extends RecyclerView.Adapter<AdapterToOrders.Ordersholder> {
    ArrayList<ConteynerToOrders> orders;

    public AdapterToOrders(ArrayList<ConteynerToOrders> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public Ordersholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewforordersBinding binding = ViewforordersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Ordersholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Ordersholder holder, int position) {
        holder.binding.ordertoname1.setText(orders.get(position).getProduct_name());
        holder.binding.ordertomodel1.setText(orders.get(position).getProduct_model());
        Picasso.get().load(orders.get(position).getProduct_picture()).into(holder.binding.ordertoimage1);

        String status = orders.get(position).getStatus();
        if(status.equals("1")){
            holder.binding.ordercheck.setBackgroundResource(R.drawable.checked);
        }else if (status.equals("2")){
            holder.binding.ordercheck.setBackgroundResource(R.drawable.check);
            holder.binding.ordertomodel1.setTextColor(Color.BLACK);
            holder.binding.ordertoname1.setTextColor(Color.BLACK);
        }}


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Ordersholder extends RecyclerView.ViewHolder{
        ViewforordersBinding binding;

        public Ordersholder(@NonNull  ViewforordersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
