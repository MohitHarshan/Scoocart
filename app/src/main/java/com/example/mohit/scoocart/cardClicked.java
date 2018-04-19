package com.example.mohit.scoocart;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class cardClicked extends AppCompatActivity implements Notificationfrag.OnFragmentInteractionListener {

    HashMap<String, String> id;
    public CheckBox wishes;
    ImageView pager1;
    ServiceHandlerTwo serviceHandler = new ServiceHandlerTwo();
    public TextView title, subtitle, pricee, product_id, category;
    TextView details;


    Button addtocart, buynow;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notify) {
            Intent intent = new Intent(this, navigation.class);
            startActivity(intent);

        } else if (id == R.id.cart) {
            Intent intent = new Intent(this, navigation.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

    TextView brand;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_clicked);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.producttitle);
        subtitle = (TextView) findViewById(R.id.productsubtitile);
        pricee = (TextView) findViewById(R.id.productprice);
        brand = (TextView) findViewById(R.id.brand);
        product_id = (TextView) findViewById(R.id.productid);
        category = (TextView) findViewById(R.id.category);
        details = (TextView) findViewById(R.id.details);

        wishes = (CheckBox) findViewById(R.id.wishes);

        pager1 = (ImageView) findViewById(R.id.pager1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Product name");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Product name");

        toolbar.setBackgroundColor(Color.parseColor("#256DE3"));


   wishes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            new postwish().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
       }
   });
        //Initializing the bottomNavigationView
        new starttask().execute();

        buynow = (Button) findViewById(R.id.buynow);
        addtocart = (Button) findViewById(R.id.addtocart);


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();


            }
        });


    }


    class postwish extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            if(wishes.isChecked()){
                ServiceHandlerTwo serviceHandler = new ServiceHandlerTwo();
                HashMap<String, String> id = new HashMap<>();


                id.put("cust_id", "253");
                id.put("products[]", getIntent().getExtras().getString("product_idw"));
                HttpHandler sho = new HttpHandler();
                String response = "";
                try {
                    response = serviceHandler.performPostCall("http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/addtowishlist", id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("Response######", response);
                try {
                    JSONObject jsonObj = null;
                    jsonObj = new JSONObject(response);
                    String status = jsonObj.getString("status");

                    if (status.equals("success")) {
                        String message = jsonObj.getString("message");
                        Log.e("Message", message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            return null;
        }
    }


    class starttask extends AsyncTask<Void, Void, Void> {
        String discount;
        String price;
        String productname;
        String product_ids;
        String model;
        String description;
        String category_name;
        String quantity;
        String ids;
        String images;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            title.setText(productname);
            subtitle.setText(model);
            pricee.setText(price);
            details.setText(description);
            product_id.setText(product_ids);
            category.setText(category_name);
            brand.setText(model);

            Picasso.with(getApplicationContext()).load("http://iroidtech.com/schoolapp/image/" + images).into(pager1);

        }

        @Override
        protected Void doInBackground(Void... voids) {


            id = new HashMap<String, String>();
            ids = getIntent().getExtras().getString("product_idw", "1710");
            id.put("product_id", ids);

            String response = "";
            try {
                response = serviceHandler.performPostCall("http://iroidtech.com/schoolapp/index.php?route=api/skoolcart/productDetails", id);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("LOG RES ###############", response);


            Log.e(TAG, "Response from url: " + response);
            if (response != null) {


                Log.e(TAG, "Response from url: " + response);

                try {
                    JSONObject jsonObj = new JSONObject(response);


                    String status = jsonObj.getString("status");

                    if (status.equals("success")) {
                        JSONArray data = jsonObj.getJSONArray("data");

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject c = data.getJSONObject(i);
                            productname = c.getString("productname");
                            product_ids = c.getString("product_id");
                            discount = c.getString("discount");
                            price = c.getString("price");
                            description = c.getString("description");
                            category_name = c.getString("category_name");
                            quantity = c.getString("quantity");
                            model = c.getString("model");
                            images = c.getString("image");


                        }


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            return null;
        }


    }






    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
