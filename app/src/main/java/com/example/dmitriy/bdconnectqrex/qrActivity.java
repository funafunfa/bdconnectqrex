package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SWEET_ID;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.productsList;


public class qrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        //setContentView(R.layout.activity_qr);
        //main();
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
        for (int i = 0; i <= productsList.size()-1;i++){
            if(productsList.get(i).containsValue(result.toString())){
                Intent intent = new Intent(qrActivity.this, OneProductActivity.class);
                intent.putExtra("code", productsList.get(i).get(TAG_SWEET_ID));
                startActivity(intent);
            }
        }
        /*for (DataBase data_obj : arrayList) {
            if (result.getText().equals(data_obj.getData_ID())) {
                Intent intent = new Intent(MainActivity.this, ProdActivity.class);
                intent.putExtra("ProdId", data_obj.getData_ID());
                finish();
                startActivity(intent);

                break;
            }
        }*/
    }
}
