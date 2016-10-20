package com.example.dmitriy.bdconnectqrex;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.apache.http.*;

import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllProductsActivity extends  ListActivity {

    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    static ArrayList<HashMap<String, String>> productsList;
    private static String url_all_products = "http://193.151.106.187/get_all_products.php";

    static final String TAG_SUCCESS = "success";
    static final String TAG_PRODUCTS = "sweets";
    static final String TAG_PID = "sweet_id";
    static final String TAG_NAME = "sweet_name";
    static final String TAG_SWEET_ID = "sweet_id";

    JSONArray sweets = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        productsList = new ArrayList<HashMap<String, String>>();

        new LoadAllProducts().execute();

        ListView lv = getListView();

    }
    public void exe(){
        new LoadAllProducts().execute();
    }
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
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
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    sweets = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < sweets.length(); i++) {
                        JSONObject c = sweets.getJSONObject(i);

                        // Storing each json item in variable
                        String sweet_id = c.getString(TAG_SWEET_ID);
                        String name = c.getString(TAG_NAME);
                        //String sweet_id = c.getString(TAG_SWEET_ID);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, sweet_id);
                        map.put(TAG_NAME, name);
                        //map.put(TAG_SWEET_ID, sweet_id);

                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
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
                            R.layout.list_item, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}
