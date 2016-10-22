package com.example.dmitriy.bdconnectqrex;

import android.app.Application;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.LoggingEventHandler;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonParser;

import org.apache.http.*;

import org.json.*;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
//import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.example.dmitriy.bdconnectqrex.ProductCreater.productCreaters;

public class AllProductsActivity extends  ListActivity {
    private ProgressDialog pDialog;
    static JSONParser jParser = new JSONParser();
    static ArrayList<HashMap<String, String>> productsList;

    static final String TAG_SUCCESS = "success";
    static final String TAG_PRODUCTS = "sweets";
    static final String TAG_PID = "sweet_id";
    static final String TAG_NAME = "sweet_name";
    static final String TAG_SWEET_ID = "sweet_id";
    static final String TAG_SWEET_DESCRIPTION = "sweet_description";
    static final String TAG_URL_IMG = "img_url";


    //final String FILENAME = "array";
    //final String FILENAME_SECOND = "hashmap";
    //String filePath =  "/data/data/fileName.txt";
    //String filePath2 = "/data/data/fileName2.txt";
    //boolean deleted = false;
    //boolean created = false;


    JSONArray sweets = null;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_STRING = "string";
    static SharedPreferences preference;
    static JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        productsList = new ArrayList<>();

        preference = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean b_IsConnect = isNetworkAvailable();
        new LoadAllProducts().execute();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Intent intent = new Intent(AllProductsActivity.this, OneProductActivity.class);
                intent.putExtra("code", productCreaters.get(position).getQr_id());
                intent.putExtra("net", "local");
                startActivity(intent);
            }
        });

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }



    class LoadAllProducts extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            Log.d("nachalo", "nachalko");
            if (preference.contains(APP_PREFERENCES_STRING)) {
                // Получаем число из настроек
                try {
                    json = new JSONObject(preference.getString(APP_PREFERENCES_STRING, " "));
                    Log.d("double shit", json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Log.d("horosho", "horosho");
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        // products found
                        // Getting Array of Products
                        sweets = json.getJSONArray(TAG_PRODUCTS);
                        SharedPreferences.Editor editor = preference.edit();
                        editor.putString(APP_PREFERENCES_STRING, json.toString());
                        editor.apply();
                        Log.d("editor","shit" + json.toString());


                        // looping through All Products
                        for (int i = 0; i < sweets.length(); i++) {
                            JSONObject c = sweets.getJSONObject(i);

                            // Storing each json item in variable
                            String sweet_id = c.getString(TAG_SWEET_ID);
                            String name = c.getString(TAG_NAME);
                            String description = c.getString(TAG_SWEET_DESCRIPTION);
                            String url_img = c.getString(TAG_URL_IMG);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<>();
                            productCreaters.add(i, new ProductCreater(name, description, sweet_id, url_img));
                            //products_name.add(i,name);
                            // adding each child node to HashMap key => value
                            map.put(TAG_PID, sweet_id);
                            map.put(TAG_NAME, name);
                            map.put(TAG_SWEET_ID, sweet_id);

                            // adding HashList to ArrayList
                            productsList.add(map);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("pizdez", "pizdec");
                List<NameValuePair> params = new ArrayList<>();
                // getting JSON string from URL
                String url_all_products = "http://193.151.106.187/get_all_products.php";

                json = jParser.makeHttpRequest(url_all_products, "GET", params);



                // Check your log cat for JSON reponse
                Log.d("All Products: ", json.toString());

                try {
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        // products found
                        // Getting Array of Products
                        sweets = json.getJSONArray(TAG_PRODUCTS);
                        SharedPreferences.Editor editor = preference.edit();
                        editor.putString(APP_PREFERENCES_STRING, json.toString());
                        editor.apply();
                        Log.d("editor","shit" + json.toString());


                        // looping through All Products
                        for (int i = 0; i < sweets.length(); i++) {
                            JSONObject c = sweets.getJSONObject(i);

                            // Storing each json item in variable
                            String sweet_id = c.getString(TAG_SWEET_ID);
                            String name = c.getString(TAG_NAME);
                            String description = c.getString(TAG_SWEET_DESCRIPTION);
                            String url_img = c.getString(TAG_URL_IMG);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<>();
                            productCreaters.add(i, new ProductCreater(name, description, sweet_id,url_img));
                            //products_name.add(i,name);
                            // adding each child node to HashMap key => value
                            map.put(TAG_PID, sweet_id);
                            map.put(TAG_NAME, name);
                            map.put(TAG_SWEET_ID, sweet_id);

                            // adding HashList to ArrayList
                            productsList.add(map);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[]{TAG_PID,
                            TAG_NAME},
                            new int[]{R.id.pid, R.id.name});
                    //ArrayAdapter adapter = new ArrayAdapter(AllProductsActivity.this,R.layout.list_item, products_name);

                    // updating listview
                    //ListAdapter adapter = new ArrayAdapter<String>(AllProductsActivity.this, R);
                    setListAdapter(adapter);
                }
            });

        }

    }
}
