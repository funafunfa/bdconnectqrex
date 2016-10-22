package com.example.dmitriy.bdconnectqrex;

import java.io.Serializable;
import java.util.ArrayList;



public class ProductCreater implements Serializable {
    private String name, description, qr_id, picture_url;
    static ArrayList<ProductCreater> productCreaters = new ArrayList<>();

    ProductCreater(String name,String description, String qr_id, String picture_url){
        this.name = name;
        this.description = description;
        this.qr_id = qr_id;
        this.picture_url = picture_url;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getQr_id() {
        return qr_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQr_id(String qr_id) {
        this.qr_id = qr_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
