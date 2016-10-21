package com.example.dmitriy.bdconnectqrex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import static com.example.dmitriy.bdconnectqrex.ProductCreater.productCreaters;

public class OneProductActivity extends AppCompatActivity {
TextView name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_product);
        //productsList;

        name = (TextView)findViewById(R.id.textName);
        description = (TextView)findViewById(R.id.textDescription);
        String code_exepted = getIntent().getStringExtra("code");

        for (int i = 0; i <= productCreaters.size()-1;i++){

            if(productCreaters.get(i).getQr_id().equals(code_exepted)){
                name.setText(productCreaters.get(i).getName());
                description.setText(productCreaters.get(i).getDescription());
            }
        }
    }
}
