package com.example.sananismayilov.myprojectsale.Adapters;

public class ConteynerToOrders {
    private String product_name;
    private String product_model;
    private String status;
    private  String product_picture;

    public ConteynerToOrders(String product_name, String product_model, String status, String product_picture) {
        this.product_name = product_name;
        this.product_model = product_model;
        this.status = status;
        this.product_picture = product_picture;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_model() {
        return product_model;
    }

    public void setProduct_model(String product_model) {
        this.product_model = product_model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }
}
