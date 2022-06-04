package com.example.hand_bag;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hand_bag.Api.ApiRequest;
import com.example.hand_bag.Api.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

public class AddToCartActivity extends AppCompatActivity {
  RelativeLayout relativeLayout;
    public SharedPreferences.Editor myEditor;
    SharedPreferences sharedpreferences;
    TextView txt_des;
    TextView txt_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        sharedpreferences =getSharedPreferences("login", Context.MODE_PRIVATE);
        String quantitiy  =sharedpreferences.getString("quantity","");
        relativeLayout=findViewById(R.id.RL_layout);
        txt_des=findViewById(R.id.items);
       txt_price=findViewById(R.id.txt_price) ;
       int fix_price= 222;
        Double price_fix = Double.parseDouble(String.valueOf(fix_price));
        Double items = Double.parseDouble(quantitiy);
        Double total_price = items * price_fix;
        txt_des.setText(   "Toal Items :"+  quantitiy);
        txt_price.setText(  ""+ total_price);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           ///     startActivity(new Intent(AddToCartActivity.this, ProfileScreen.class));
              //  overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

}