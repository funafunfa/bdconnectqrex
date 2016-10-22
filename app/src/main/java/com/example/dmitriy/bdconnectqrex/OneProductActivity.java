package com.example.dmitriy.bdconnectqrex;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static com.example.dmitriy.bdconnectqrex.ProductCreater.productCreaters;

public class OneProductActivity extends AppCompatActivity {
TextView name, description, url;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_product);
        //productsList;

        name = (TextView)findViewById(R.id.textName);
        description = (TextView)findViewById(R.id.textDescription);
        //url = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);


        String code_exepted = getIntent().getStringExtra("code");
        //String code_local = getIntent().getStringExtra("net");
        //getBooleanExtra("net",false);

            for (int i = 0; i <= productCreaters.size() - 1; i++) {

                if (productCreaters.get(i).getQr_id().equals(code_exepted)) {
                    name.setText(productCreaters.get(i).getName());
                    description.setText(productCreaters.get(i).getDescription());
                    //url.setText(productCreaters.get(i).getPicture_url());
                    Picasso.with(this).load(productCreaters.get(i).getPicture_url()).transform(new CircularTransformation()).into(imageView);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        }
    public class CircularTransformation implements Transformation {

        public CircularTransformation() {
        }

        @Override
        public Bitmap transform(final Bitmap source) {
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            final Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(output);
            canvas.drawCircle(source.getWidth() / 3, source.getHeight() / 3, source.getWidth() / 3, paint);

            if (source != output)
                source.recycle();

            return output;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
