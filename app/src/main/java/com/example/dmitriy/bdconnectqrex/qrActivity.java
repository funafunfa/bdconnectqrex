package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

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
            if(productCreaters.get(i).getQr_id().equals(result.toString())){
                Intent intent = new Intent(qrActivity.this, OneProductActivity.class);
                intent.putExtra("code", productCreaters.get(i).getQr_id());
                startActivity(intent);
            }
        }
    }
}
