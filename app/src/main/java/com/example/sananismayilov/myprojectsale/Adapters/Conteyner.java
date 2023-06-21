package com.example.sananismayilov.myprojectsale.Adapters;

import java.io.Serializable;

public class Conteyner implements Serializable {
    public String picture;
    public String ad;
    public String model;
    public String qiymet;
    public int id;

    public Conteyner(String picture, String ad, String model, String qiymet) {
        this.picture = picture;
        this.ad = ad;
        this.model = model;
        this.qiymet = qiymet;
    }

    public Conteyner(String picture, String ad, String model, String qiymet, int id) {
        this.picture = picture;
        this.ad = ad;
        this.model = model;
        this.qiymet = qiymet;
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQiymet() {
        return qiymet;
    }

    public void setQiymet(String qiymet) {
        this.qiymet = qiymet;
    }
}
