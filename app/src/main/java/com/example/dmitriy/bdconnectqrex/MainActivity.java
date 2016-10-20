package com.example.dmitriy.bdconnectqrex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        finish();
    }


    public void qr(View view) {
        Intent intent = new Intent(MainActivity.this, qrActivity.class);
        startActivity(intent);
    }
}
