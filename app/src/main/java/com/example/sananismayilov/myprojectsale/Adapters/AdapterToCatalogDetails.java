package com.example.sananismayilov.myprojectsale.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sananismayilov.myprojectsale.databinding.ViewforcatalogfragmentdetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterToCatalogDetails extends RecyclerView.Adapter<AdapterToCatalogDetails.CatalogDetailsHolder> {
ArrayList<ConteynerToCatalogDetails> catalogDetails;

    public AdapterToCatalogDetails(ArrayList<ConteynerToCatalogDetails> catalogDetails) {
        this.catalogDetails = catalogDetails;
    }

    @NonNull
    @Override
    public CatalogDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewforcatalogfragmentdetailsBinding binding = ViewforcatalogfragmentdetailsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CatalogDetailsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogDetailsHolder holder, int position) {
        holder.binding.catalogdetailsactivityname.setText(catalogDetails.get(position).getName());
        holder.binding.catalogdetailsactivitymodel.setText(catalogDetails.get(position).getModel());
        holder.binding.catalogdetailsactivityqiymet.setText(catalogDetails.get(position).getPrice()+" AZN");
        Picasso.get().load(catalogDetails.get(position).getPicture()).into(holder.binding.catalogdetailsactivitypicture);


    }

    @Override
    public int getItemCount() {
        return catalogDetails.size();
    }

    public class CatalogDetailsHolder extends  RecyclerView.ViewHolder{
        ViewforcatalogfragmentdetailsBinding binding;
        public CatalogDetailsHolder(@NonNull ViewforcatalogfragmentdetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
