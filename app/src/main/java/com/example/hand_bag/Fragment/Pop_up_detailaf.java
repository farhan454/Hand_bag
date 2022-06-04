package com.example.hand_bag.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hand_bag.AddToCartActivity;
import com.example.hand_bag.Coupin.SlideCouponPagerAdapter;
import com.example.hand_bag.HelperClass.BagsHelperClass;
import com.example.hand_bag.R;
import com.example.hand_bag.SlidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Pop_up_detailaf  extends DialogFragment implements GestureDetector.OnGestureListener{
    LinearLayout img_finish;
    int rating_star, selected_c_pos, selected_f_pos;
    BagsHelperClass bagsHelperClass;
    RelativeLayout relativeLayout;
    RadioGroup radioGroup;
    Button btn_buy;
    RadioButton radio_blue,radio_purple,radio_red;
    LinearLayout immg_shop;
    GestureDetector gestureScanner;
    LinearLayout main_gesture;
    GestureDetector gestureDetector;
    ImageView img_bag;
    TextView txt_plus,txt_negative,txt_num;
    int count=1;
    int pagerCurrentPosition = 0;
    private SlideCouponPagerAdapter pagerAdapter;
    private List<Object> sliding_coupon_list = new ArrayList<>();
    ImageView img_fvrt;
    boolean isclicked=true;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;
    Context appContext;
    Dialog dialog;
    RecyclerView sliding_recycler_View;
    ViewPager viewPager;
    private static  Pop_up_detailaf instance = null;
    public static Pop_up_detailaf getInstance() {
        return instance;
    }



    public Pop_up_detailaf() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (appContext == null)
            appContext = context.getApplicationContext();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        try {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        try {

            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
            // Assign window properties to fill the parent
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Call super onResume after sizing
        super.onResume();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_pop_up_detaila, new RelativeLayout(getActivity()), false);
        instance = this;
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        img_finish=dialog.findViewById(R.id.finish);
        img_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        txt_num=dialog.findViewById(R.id.num);
        main_gesture=dialog.findViewById(R.id.main_gesture_layout);
        txt_negative=dialog.findViewById(R.id.negative);
        sliding_recycler_View=dialog.findViewById(R.id.slide_recycler_View);
        viewPager=dialog.findViewById(R.id.ViewPager);
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
        if (getArguments() != null) {
            bagsHelperClass = (BagsHelperClass) getArguments().getSerializable("COUPON");
            selected_c_pos = getArguments().getInt("coupon_pos");
            selected_f_pos = getArguments().getInt("coupon_pos");
            sliding_coupon_list = (List<Object>) getArguments().getSerializable("Coupons_List");
            //Log.e("@Coupons_List@", "Size = " + sliding_coupon_list.size());
        }
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
      //  Window window = instance.getWindow();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
        String title =sharedPreferences.getString("title","");
        String url =sharedPreferences.getString("url","");
        String author =sharedPreferences.getString("author","");
        String des =sharedPreferences.getString("des","");
        Glide.with(instance).load(url).into(img_bag);



// clear FLAG_TRANSLUCENT_STATUS flag:
      //  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
      ////  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
    //    window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.red));
   //     relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.women_bag_1_bg));
        radio_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    img_bag.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(Detail_bag_activity.this,R.color.blue)));
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.womens_bag_2));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_blue));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg_blue));
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
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.women_bag_1_bg));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_red));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg));
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
                relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.womens_bag_4));
                btn_buy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_bg_purple));
                immg_shop.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cart_bg_purple));
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
                instance.startActivity(new Intent(getContext(), AddToCartActivity.class));
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                myEditor = sharedPreferences.edit();
                String  quantiity = String.valueOf(count);
                myEditor.putString("quantity", quantiity);
                myEditor.commit();
            }
        });
        //sliding_recycler_View.setVisibility(View.VISIBLE);
       viewPager.setVisibility(View.VISIBLE);
        pagerAdapter = new SlideCouponPagerAdapter(getActivity(),sliding_coupon_list , dialog, viewPager,"1");
        viewPager.setRotationY(0);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(selected_c_pos);
        viewPager.setCurrentItem(selected_f_pos);
        pagerAdapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e("TAG", "onPageScrolled: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG", "onPageSelected: " + position);
                pagerCurrentPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Log.e("TAG", "onPageScrollStateChanged: "+state);
            }
        });
        gestureScanner = new GestureDetector(this);
        main_gesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dialog.dismiss();
                return gestureScanner.onTouchEvent(event);
            }
        });
        /**********************************************************************************/
        /*.... For swipe down coupon layout code here....*/
        /**********************************************************************************/
     /*   gestureDetector = new GestureDetector(getContext(), new MyGestureDetector());
        gestureLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });*/
        return dialog;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}