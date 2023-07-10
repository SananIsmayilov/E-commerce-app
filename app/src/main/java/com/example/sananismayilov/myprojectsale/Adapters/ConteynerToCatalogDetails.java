package com.example.sananismayilov.myprojectsale.Adapters;

public class ConteynerToCatalogDetails {
    private  String name;
    private  String model;
    private  String price;
    private  String picture;

    public ConteynerToCatalogDetails(String name, String model, String price, String picture) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
