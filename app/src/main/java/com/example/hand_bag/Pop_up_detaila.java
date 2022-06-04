package com.example.hand_bag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class Pop_up_detaila extends Dialog {
    LinearLayout img_finish;
    RelativeLayout relativeLayout;
    RadioGroup radioGroup;
    Button btn_buy;
    RadioButton radio_blue,radio_purple,radio_red;
    LinearLayout immg_shop;
    ImageView img_bag;
    TextView txt_plus,txt_negative,txt_num;
    int count=1;
    ImageView img_fvrt;
    boolean isclicked=true;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;
    public Pop_up_detaila(@NonNull Context context) {
        super(context);
    }
    public void showDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.pop_up_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        img_finish=dialog.findViewById(R.id.finish);
        img_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        txt_num=dialog.findViewById(R.id.num);
        txt_negative=dialog.findViewById(R.id.negative);
        img_fvrt=dialog.findViewById(R.id.fvet_icon);
        img_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isclicked){
                    img_fvrt.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.red)));
                    img_fvrt.setImageResource(R.drawable.ic_fill_fvrt);
                    isclicked=false;
                }else {
                    img_fvrt.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.black)));
                    img_fvrt.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    isclicked=true;
                }


            }
        });
        txt_plus=dialog.findViewById(R.id.plus);
        txt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                txt_num.setText(String.valueOf(count));
            }
        });
        txt_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 1){

                }else {
                    count--;
                    txt_num.setText(String.valueOf(count));
                }

            }
        });

        relativeLayout=dialog.findViewById(R.id.rl_main);
        btn_buy=dialog.findViewById(R.id.buy_now);
        radio_blue=dialog.findViewById(R.id.radio_btn_blue);
        radio_purple=dialog.findViewById(R.id.radio_btn_purple);
        radio_red=dialog.findViewById(R.id.radio_btn_red);
        img_bag=dialog.findViewById(R.id.img);
        immg_shop=dialog.findViewById(R.id.imgshop);
        radio_purple.setChecked(true);
        radio_red.setChecked(true);
        radio_blue.setChecked(true);
        Window window = this.getWindow();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
   String title =sharedPreferences.getString("title","");
   String url =sharedPreferences.getString("url","");
   String author =sharedPreferences.getString("author","");
   String des =sharedPreferences.getString("des","");
        Glide.with(context).load(url).into(img_bag);



// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.red));
        relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.women_bag_1_bg));
        radio_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    img_bag.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(Detail_bag_activity.this,R.color.blue)));
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.womens_bag_2));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_blue));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg_blue));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.blue));
            }
        });
        radio_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.women_bag_1_bg));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_red));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.red));
            }
        });
        radio_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.womens_bag_4));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_purple));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg_purple));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.purple));
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               context.startActivity(new Intent(getContext(), AddToCartActivity.class));
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                myEditor = sharedPreferences.edit();
              String  quantiity = String.valueOf(count);
                myEditor.putString("quantity", quantiity);
                myEditor.commit();
            }
        });
        dialog.show();
    }





    }

