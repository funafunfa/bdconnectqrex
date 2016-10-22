package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.APP_PREFERENCES_STRING;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_NAME;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_PID;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_PRODUCTS;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SUCCESS;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SWEET_DESCRIPTION;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SWEET_ID;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.jParser;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.json;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.preference;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.productsList;
import static com.example.dmitriy.bdconnectqrex.ProductCreater.productCreaters;

public class qrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    public void onClick(View view) {
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }



    @Override
    public void handleResult(Result result) {
        for (int i = 0; i <= productCreaters.size()-1;i++){
            Intent intent = new Intent(qrActivity.this, OneProductActivity.class);
            //intent.putExtra("code", productCreaters.get(i).getQr_id());
            Log.d("Path", "1");

            if(productCreaters.get(i).getQr_id().equals(result.toString())){
                //Intent intent = new Intent(qrActivity.this, OneProductActivity.class);
                intent.putExtra("code", productCreaters.get(i).getQr_id());
                //intent.putExtra("net", "local");
                Log.d("Path", "2a");
                startActivity(intent);
                finish();
            }
            }
        }
}
