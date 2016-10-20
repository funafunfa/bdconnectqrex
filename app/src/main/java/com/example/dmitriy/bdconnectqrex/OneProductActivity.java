package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_NAME;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.TAG_SWEET_ID;
import static com.example.dmitriy.bdconnectqrex.AllProductsActivity.productsList;

public class OneProductActivity extends AppCompatActivity {
TextView name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_product);
        //productsList;
        //AllProductsActivity.LoadAllProducts().execute();
        //AllProductsActivity activity = new AllProductsActivity();
        //activity.exe();
        name = (TextView)findViewById(R.id.textName);
        description = (TextView)findViewById(R.id.textDescription);
        String code_exepted = getIntent().getStringExtra("code");

        for (int i = 0; i <= productsList.size()-1;i++){
            if (productsList.get(i).containsKey(TAG_SWEET_ID) & productsList.get(i).containsValue(code_exepted)){
                name.setText(productsList.get(i).get(TAG_NAME));
                description.setText(productsList.get(i).get(TAG_SWEET_ID));
            }
        }
    }
}
