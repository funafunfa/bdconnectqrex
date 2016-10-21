package com.example.dmitriy.bdconnectqrex;

import java.io.Serializable;
import java.util.ArrayList;



public class ProductCreater implements Serializable {
    private String name, description, qr_id;
    static ArrayList<ProductCreater> productCreaters = new ArrayList<>();

    ProductCreater(String name,String description, String qr_id){
        this.name = name;
        this.description = description;
        this.qr_id = qr_id;
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


}
