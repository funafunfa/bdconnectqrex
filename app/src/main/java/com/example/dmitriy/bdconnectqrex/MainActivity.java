package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.APP_PREFERENCES_STRING;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_PRODUCTS;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SUCCESS;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.json;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.preference;


public class MainActivity extends AppCompatActivity {
    Button btnViewProducts;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.Code);
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);

        btnViewProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                startActivity(i);

            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, OneProductActivity.class);
        intent.putExtra("code", editText.getText().toString());
        startActivity(intent);
    }


    public void qr(View view) {
        Intent intent = new Intent(MainActivity.this, qrActivity.class);
        startActivity(intent);
    }

    public void onClickDB(View view) {

        }
    }
