package com.example.sananismayilov.myprojectsale.Adapters;

public class ConteynerToFragmentCart {

    private String nametofragmentcart;
    private String modeltofragmentcart;
    private String pricetofragmentcart;
    private String picturetofragmentcart;

    public ConteynerToFragmentCart(String nametofragmentcart, String modeltofragmentcart, String pricetofragmentcart, String picturetofragmentcart) {
        this.nametofragmentcart = nametofragmentcart;
        this.modeltofragmentcart = modeltofragmentcart;
        this.pricetofragmentcart = pricetofragmentcart;
        this.picturetofragmentcart = picturetofragmentcart;
    }

    public String getNametofragmentcart() {
        return nametofragmentcart;
    }

    public void setNametofragmentcart(String nametofragmentcart) {
        this.nametofragmentcart = nametofragmentcart;
    }

    public String getModeltofragmentcart() {
        return modeltofragmentcart;
    }

    public void setModeltofragmentcart(String modeltofragmentcart) {
        this.modeltofragmentcart = modeltofragmentcart;
    }

    public String getPricetofragmentcart() {
        return pricetofragmentcart;
    }

    public void setPricetofragmentcart(String pricetofragmentcart) {
        this.pricetofragmentcart = pricetofragmentcart;
    }

    public String getPicturetofragmentcart() {
        return picturetofragmentcart;
    }

    public void setPicturetofragmentcart(String picturetofragmentcart) {
        this.picturetofragmentcart = picturetofragmentcart;
    }
}
