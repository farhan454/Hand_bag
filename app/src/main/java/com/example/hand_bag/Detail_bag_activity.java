package com.example.hand_bag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class Detail_bag_activity extends AppCompatActivity {
RelativeLayout relativeLayout;
    RadioGroup radioGroup;
    Button btn_buy;
    RadioButton radio_blue,radio_purple,radio_red;
    LinearLayout immg_shop;
    ImageView img_bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bag_activity);
        relativeLayout=findViewById(R.id.rl_main);
        btn_buy=findViewById(R.id.buy_now);
        radio_blue=findViewById(R.id.radio_btn_blue);
        radio_purple=findViewById(R.id.radio_btn_purple);
        radio_red=findViewById(R.id.radio_btn_red);
        img_bag=findViewById(R.id.img);
        immg_shop=findViewById(R.id.imgshop);
        radio_purple.setChecked(true);
        radio_red.setChecked(true);
        radio_blue.setChecked(true);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.red));
        relativeLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.women_bag_1_bg));
        radio_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    img_bag.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(Detail_bag_activity.this,R.color.blue)));
                relativeLayout.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.womens_bag_2));
                       btn_buy.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.btn_bg_blue));
                immg_shop.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.cart_bg_blue));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(Detail_bag_activity.this,R.color.blue));
            }
        });
        radio_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        relativeLayout.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.women_bag_1_bg));
                        btn_buy.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.btn_bg_red));
                immg_shop.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.cart_bg));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(Detail_bag_activity.this,R.color.red));
            }
        });
        radio_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        relativeLayout.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.womens_bag_4));
                       btn_buy.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.btn_bg_purple));
                immg_shop.setBackground(ContextCompat.getDrawable(Detail_bag_activity.this, R.drawable.cart_bg_purple));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(Detail_bag_activity.this,R.color.purple));
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Detail_bag_activity.this, AddToCartActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}