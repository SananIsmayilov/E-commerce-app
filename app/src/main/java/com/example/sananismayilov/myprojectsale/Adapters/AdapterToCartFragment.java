package com.example.sananismayilov.myprojectsale.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.R;
import com.example.sananismayilov.myprojectsale.databinding.ViewforcartfragmentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterToCartFragment extends RecyclerView.Adapter<AdapterToCartFragment.CartFragmentHolder> {
ArrayList <ConteynerToFragmentCart> conteynerToFragmentCarts;

    public AdapterToCartFragment(ArrayList<ConteynerToFragmentCart> conteynerToFragmentCarts) {
        this.conteynerToFragmentCarts = conteynerToFragmentCarts;
    }

    @NonNull
    @Override
    public CartFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewforcartfragmentBinding binding = ViewforcartfragmentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CartFragmentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartFragmentHolder holder, int position) {
        if(conteynerToFragmentCarts.size()  >0 ){
        holder.binding.cartfragmentname.setText(conteynerToFragmentCarts.get(position).getNametofragmentcart());
        holder.binding.cartfragmentmodel.setText(conteynerToFragmentCarts.get(position).getModeltofragmentcart());
        holder.binding.cartfragmentqiymet.setText(conteynerToFragmentCarts.get(position).getPricetofragmentcart());
        Picasso.get().load(conteynerToFragmentCarts.get(position).getPicturetofragmentcart()).into(holder.binding.cartfragmentpicture);
    }


    }

    @Override
    public int getItemCount() {
        return conteynerToFragmentCarts.size();
    }

    public class CartFragmentHolder extends RecyclerView.ViewHolder{
        ViewforcartfragmentBinding binding;

        public CartFragmentHolder(@NonNull ViewforcartfragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
