package com.example.hand_bag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.hand_bag.HelperClass.BagsHelperClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SlidePagerAdapter extends PagerAdapter {
    LinearLayout img_finish;
    RelativeLayout relativeLayout;
    RadioGroup radioGroup;
    Button btn_buy;
    RadioButton radio_blue,radio_purple,radio_red;
    LinearLayout immg_shop;
    ImageView img_bag;
    TextView txt_plus,txt_negative,txt_num;
    int count=1;
    private SlidePagerAdapter pagerAdapter;
    private List<Object> sliding_coupon_list = new ArrayList<>();
    ImageView img_fvrt;
    boolean isclicked=true;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;
    Context appContext;
    Dialog dialog;
    RecyclerView sliding_recycler_View;
    ViewPager viewPager;
    LinearLayout linearLayout;
    Context context;
    public String s;
    // A menu item view type.
    private List<Object> mFilterRecyclerViewItems;
    String saveLanguage, type;
    String shareContent;
    GestureDetector gestureDetector;
    String appUniqueId;
    String link_text;
    private static final String LINK_TEXT_KEY = "link_text";
  //  ViewPager viewPager;
    boolean not_logged_in;
    public SlidePagerAdapter(Context context, List<Object> listCoupon, Dialog dialog, ViewPager viewPager) {
        Log.e("cons","called");
        this.context = context;
        this.mFilterRecyclerViewItems = listCoupon;
        this.dialog = dialog;
        appUniqueId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        this.viewPager = viewPager;
        this.type = type;
        RelativeLayout relativeLayout;

    }
    @Override
    public int getCount() {
        return mFilterRecyclerViewItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.e("@@call", "call");
        View v = null;
        Log.e("@@calltry", "call");
        final BagsHelperClass baga = (BagsHelperClass) mFilterRecyclerViewItems.get(position);
        Log.e("@@bags", String.valueOf(mFilterRecyclerViewItems.size()));
        //   Log.e("@Ck@", "last valid use value = " + coupon.lu_total);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.sliding_view, container, false);


        v.setTag("View" + position);
        Log.e("@@view", String.valueOf(v));
        txt_num=v.findViewById(R.id.num);
        txt_negative=v.findViewById(R.id.negative);
        relativeLayout=v.findViewById(R.id.rl_main);
        // linearLayout=v.findViewById(R.id.ll);
//            linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.pop_up_bg));
        sliding_recycler_View=v.findViewById(R.id.slide_recycler_View);
        //    viewPager=v.findViewById(R.id.ViewPager);
        img_fvrt=v.findViewById(R.id.fvet_icon);
        img_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isclicked){
                    img_fvrt.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.red)));
                    img_fvrt.setImageResource(R.drawable.ic_fill_fvrt);
                    isclicked=false;
                }else {
                    img_fvrt.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.black)));
                    img_fvrt.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    isclicked=true;
                }


            }
        });
        if (context != null) {
//                Glide.with(context.getApplicationContext()).load(baga.getImage()).placeholder(R.drawable.logo).into(img_bag);
        }

        txt_plus=v.findViewById(R.id.plus);
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

        relativeLayout=v.findViewById(R.id.rl_main);
        btn_buy=v.findViewById(R.id.buy_now);
        radio_blue=v.findViewById(R.id.radio_btn_blue);
        radio_purple=v.findViewById(R.id.radio_btn_purple);
        radio_red=v.findViewById(R.id.radio_btn_red);
        img_bag=v.findViewById(R.id.img);
        immg_shop=v.findViewById(R.id.imgshop);
        radio_purple.setChecked(true);
        radio_red.setChecked(true);
        radio_blue.setChecked(true);
        //  Window window = instance.getWindow();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
        String title =sharedPreferences.getString("title","");
        String url =sharedPreferences.getString("url","");
        String author =sharedPreferences.getString("author","");
        String des =sharedPreferences.getString("des","");
        Glide.with(context).load(url).into(img_bag);



// clear FLAG_TRANSLUCENT_STATUS flag:
        //  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        ////  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        //    window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.red));
        //      relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.women_bag_1_bg));
        radio_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    img_bag.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(Detail_bag_activity.this,R.color.blue)));
                relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.womens_bag_2));
                btn_buy.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_bg_blue));
                immg_shop.setBackground(ContextCompat.getDrawable(context, R.drawable.cart_bg_blue));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                //     window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                //      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                //      window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.blue));
            }
        });
        radio_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.women_bag_1_bg));
                btn_buy.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_bg_red));
                immg_shop.setBackground(ContextCompat.getDrawable(context, R.drawable.cart_bg));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                //    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG/_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                //    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                //   window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.red));
            }
        });
        radio_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.womens_bag_4));
                btn_buy.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_bg_purple));
                immg_shop.setBackground(ContextCompat.getDrawable(context, R.drawable.cart_bg_purple));
                // clear FLAG_TRANSLUCENT_STATUS flag:
                //    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                ///     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                ///    window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.purple));
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddToCartActivity.class));
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                myEditor = sharedPreferences.edit();
                String  quantiity = String.valueOf(count);
                myEditor.putString("quantity", quantiity);
                myEditor.commit();
            }
        });
        //sliding_recycler_View.setVisibility(View.VISIBLE);
        return v;

    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        try {
            container.removeView((View) object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
