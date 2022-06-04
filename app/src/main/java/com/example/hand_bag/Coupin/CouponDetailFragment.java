package com.example.hand_bag.Coupin;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.example.hand_bag.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by IT Empire 6/May/2020
 */
public class CouponDetailFragment extends DialogFragment implements GestureDetector.OnGestureListener {
    @BindView(R.id.ar_copyBtnClick_layout)
    LinearLayout ar_copyBtnClick_layout;
    @BindView(R.id.eng_copyBtnClick_layout)
    LinearLayout eng_copyBtnClick_layout;
    @BindView(R.id.copybtn_design)
    RelativeLayout copybtn_design;
    @BindView(R.id.likeBtn)
    ImageView likeBtn;
    @BindView(R.id.ar_sc_rel)
    RelativeLayout ar_sc_rel;
    @BindView(R.id.eng_sc_rel)
    RelativeLayout eng_sc_rel;
    @BindView(R.id.ar_crossbtn)
    RelativeLayout crossbtn;
    @BindView(R.id.ar_sharebtn)
    RelativeLayout sharebtn;
    @BindView(R.id.dislikeBtn)
    ImageView dislikeBtn;
    @BindView(R.id.last_used_coupon_txt)
    TextView last_used_coupon_txt;
    @BindView(R.id.coupon_detail_imgs)
    ImageView coupon_detail_img;
    @BindView(R.id.c_name)
    TextView c_name;
    @BindView(R.id.discount_tex)
    TextView discount_text;
    @BindView(R.id.admin_detail_text)
    TextView admin_detail_text;
    @BindView(R.id.detail_text)
    TextView detail_text;
    @BindView(R.id.copy_code_text)
    TextView copy_code_text;
    @BindView(R.id.eng_copy_code_text)
    TextView eng_copy_code_text;
    @BindView(R.id.link_text)
    TextView show_link_text;
    @BindView(R.id.btnLink)
    ImageView btnLink;
    @Nullable
    @BindView(R.id.gestureLayout)
    LinearLayout gestureLayout;
    @BindView(R.id.eng_copied_lin)
    LinearLayout eng_copied_lin;
    @BindView(R.id.ar_copied_lin)
    LinearLayout ar_copied_lin;
    @Nullable
    @BindView(R.id.main_gesture_layout)
    LinearLayout main_gesture_layout;
    @BindView(R.id.ar_shareImg)
    ImageView shareImg;
    @BindView(R.id.ar_crossImg)
    ImageView crossImg;
    @BindView(R.id.eng_shareImg)
    ImageView eng_shareImg;
    @BindView(R.id.eng_crossImg)
    ImageView eng_crossImg;
    @BindView(R.id.fav_press)
    ImageView fav_press;
    @BindView(R.id.with_out_sliding_view)
    LinearLayout with_out_sliding_view;
    @BindView(R.id.slide_recycler_View)
    RecyclerView sliding_recycler_View;
    @BindView(R.id.ViewPager)
    ViewPager viewPager;
    Dialog dialog;
    CouponDetails coupon;
    String is_coupons_slide, type;
    String appUniqueId;
    String shareContent = "";
    private SharedPreferences CustomRatePref;
    private SharedPreferences.Editor CustomRatePrefEditor;
    private Boolean saveLogin;
    GetApiParameters apiParameters;
    private int shortAnimationDuration = 1000;
    GestureDetector gestureScanner;
    GestureDetector gestureDetector;
    int rating_star, selected_c_pos, selected_f_pos;
    private FirebaseAnalytics mFirebaseAnalytics;
    private String saveLanguage;
    private List<Object> sliding_coupon_list = new ArrayList<>();
    int pagerCurrentPosition = 0;
    private static CouponDetailFragment fragment = null;
    private static CouponDetailFragment instance = null;
    private SlideCouponPagerAdapter pagerAdapter;
    public static boolean isSlidingCouponVisible = false;
    ArrayList<Integer> selectedIndexArray = new ArrayList<>();
    String link_text;
    private static final String LINK_TEXT_KEY = "link_text";
    Context appContext;

    boolean not_logged_in;
    private long mLastClickTime = System.currentTimeMillis();
    private static final long CLICK_TIME_INTERVAL = 1000;
    private boolean isDarkMode;

    private String PROGRESS_DIALOG = "CDFragment";
    String pressed;
    AppEventsLogger logger;

    public static CouponDetailFragment getInstance() {
        return instance;
    }


    public CouponDetailFragment() {
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

        View view = getActivity().getLayoutInflater().inflate(R.layout.couponscreen, new LinearLayout(getActivity()), false);
        // Build dialog
        Log.e("@@Coupon_dialogue", "dialogue visible");
        ButterKnife.bind(this, view);
        instance = this;
        dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(view);
        logger = AppEventsLogger.newLogger(getActivity());
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        CouponCopied("");
        CouponFav("");
        CouponLiked("");
        CouponDisLiked("");
        CouponWebLink("");
        CouponRemoveFromFav("");


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        appUniqueId = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        CustomRatePref = getActivity().getSharedPreferences("CustomRatePref", MODE_PRIVATE);
        CustomRatePrefEditor = CustomRatePref.edit();

        if (getArguments() != null) {
            coupon = (CouponDetails) getArguments().getSerializable("COUPON");
            is_coupons_slide = getArguments().getString("Slide_Coupon");
            type = getArguments().getString("type");
            selected_c_pos = getArguments().getInt("coupon_pos");
            selected_f_pos = getArguments().getInt("coupon_pos");
            if (is_coupons_slide.equalsIgnoreCase("1")) {
                sliding_coupon_list = (List<Object>) getArguments().getSerializable("Coupons_List");
            }
            //Log.e("@Coupons_List@", "Size = " + sliding_coupon_list.size());
        }

        isSlidingCouponVisible = true;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        with_out_sliding_view.setVisibility(View.GONE);
        //sliding_recycler_View.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        pagerAdapter = new SlideCouponPagerAdapter(getActivity(), sliding_coupon_list, dialog, viewPager, type);
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



        if (getActivity() != null) {
            Glide.with(getActivity().getApplicationContext()).load(coupon.imgUrl).into(coupon_detail_img);
        }
        /**********************************************************************************/
        /* For Language Check English OR Arabic and Show Data According to the language */
        /**********************************************************************************/
        ar_copyBtnClick_layout.setVisibility(View.GONE);
        ar_sc_rel.setVisibility(View.GONE);
        ar_copied_lin.setVisibility(View.GONE);
        eng_copied_lin.setVisibility(View.VISIBLE);
        eng_sc_rel.setVisibility(View.VISIBLE);

        eng_copyBtnClick_layout.setVisibility(View.VISIBLE);
        c_name.setText("" + coupon.arabicName);
        if (coupon.getEng_discount().equalsIgnoreCase("") || coupon.getEng_details().equalsIgnoreCase("null")) {
            detail_text.setText("");
        } else {
            String dt = coupon.eng_details.replace("\n", " ");
            detail_text.setText("" + dt);
            detail_text.setMovementMethod(new ScrollingMovementMethod());
        }
        eng_copy_code_text.setText("" + coupon.code);
        discount_text.setText("" + coupon.eng_discount);
        admin_detail_text.setText("" + coupon.eng_brief);
        /**********************************************************************************/
        ///*** This code for Last Time Used Coupon Information Detail Handle and show in TextView ***///
        /**********************************************************************************/
        if (coupon.lu_type.equalsIgnoreCase("") && coupon.lu_total.equalsIgnoreCase("")) {
            //** Not show any thing when coupon not used **//
        } else {
            //** For seconds **//
            if (coupon.lu_type.equalsIgnoreCase("0")) {
                last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_second) + " " + getResources().getString(R.string.str_ago));
            }
            //** For minutes **//
            else if (coupon.lu_type.equalsIgnoreCase("1")) {
                last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_minute) + " " + getResources().getString(R.string.str_ago));
            }
            //** For hours **//
            else if (coupon.lu_type.equalsIgnoreCase("2")) {
                if (coupon.lu_total.equalsIgnoreCase("0")) {
                    last_used_coupon_txt.setText("" + getResources().getString(R.string.str_ls_lh));
                } else if (coupon.lu_total.equalsIgnoreCase("1")) {
                    last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_hour));
                } else if (coupon.lu_total.equalsIgnoreCase("2")) {
                    if (saveLanguage.equalsIgnoreCase("ar")) {
                        last_used_coupon_txt.setText(" " + getResources().getString(R.string.str_hourss));
                    } else if (saveLanguage.equalsIgnoreCase("en")) {
                        last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_hours));
                    }
                } else if (Integer.parseInt(coupon.lu_total) > 2 & Integer.parseInt(coupon.lu_total) <= 10) {
                    last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_hours));
                } else if (Integer.parseInt(coupon.lu_total) > 10) {
                    last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_hour));
                }
            }
            //** For days **//
            else if (coupon.lu_type.equalsIgnoreCase("3")) {
                if (coupon.lu_total.equalsIgnoreCase("1")) {
                    last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_day));
                } else if (coupon.lu_total.equalsIgnoreCase("2")) {
                    if (saveLanguage.equalsIgnoreCase("ar")) {
                        last_used_coupon_txt.setText(" " + getResources().getString(R.string.str_days));
                    } else if (saveLanguage.equalsIgnoreCase("en")) {
                        last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_days));
                    }
                } else if (Integer.parseInt(coupon.lu_total) > 2) {
                    last_used_coupon_txt.setText("" + coupon.lu_total + " " + getResources().getString(R.string.str_dayss));
                }

            }
            //** For week **//
            else if (coupon.lu_type.equalsIgnoreCase("4")) {
                last_used_coupon_txt.setText(" " + getResources().getString(R.string.str_week));
            }

        }
        //last_used_coupon_txt.setText("");
        /**********************************************************************************/
        /*....Touch OutSide the layout to dismiss the coupon ....*/
        /**********************************************************************************/
        gestureScanner = new GestureDetector(this);
        main_gesture_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureScanner.onTouchEvent(event);
            }
        });
        /**********************************************************************************/
        /*.... For swipe down coupon layout code here....*/
        /**********************************************************************************/
        gestureDetector = new GestureDetector(getContext(), new MyGestureDetector());
        gestureLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
     //   shareMsgApi();

        fav_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.logEvent("CouponFav");
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                    return;
                }
                mLastClickTime = now;
           //     checkUserLoginState(coupon, fav_press);

            }
        });
        /**********************************************************************************/
        /*
         *//* For Checking Screen Shot Taken From User Code Start *//*
         *//**********************************************************************************/

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(appContext, "Not Granted", Toast.LENGTH_SHORT).show();
        } else {

        }

        /**********************************************************************************/
        /* For Checking Screen Shot Taken From User Code End */
        /**********************************************************************************/
        return dialog;
    }



    private void applyValue(String ar_con, String en_con) {
        try {
            if (saveLanguage.equalsIgnoreCase("ar")) {
                show_link_text.setText("" + ar_con);
            } else if (saveLanguage.equalsIgnoreCase("en")) {
                show_link_text.setText("" + en_con);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getCurrentItem() {
        return ((LinearLayoutManager) sliding_recycler_View.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }




    public void showCouponDetailDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.hide();
        }
    }

    /**********************************************************************************/
    /*....Add Favorite Coupon  API Call....*//**
     * @param coupon
     * @param fav_press

     ********************************************************************************/

    /**********************************************************************************/
    /*....WEBSITE Link Counter API Call....*/

    /**
     * @param coupon
     ********************************************************************************/

    /**********************************************************************************/
    /*....Button Click Handle....*/

    /**********************************************************************************/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.ar_copyBtnClick_layout, R.id.eng_copyBtnClick_layout, R.id.likeBtn, R.id.dislikeBtn, R.id.ar_crossbtn, R.id.ar_sharebtn, R.id.eng_crossbtn, R.id.eng_sharebtn})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.ar_copyBtnClick_layout:

                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", "" + coupon.code);
                    clipboard.setPrimaryClip(clip);
                 //   copyCodeAdjustEvent();
                    logger.logEvent("CouponCopied");
                    ar_copyBtnClick_layout.setVisibility(View.GONE);
                    copybtn_design.setVisibility(View.VISIBLE);
                //    new FadeInAnimation(copybtn_design).setDuration(700).animate();
                    coupon_copy_call(coupon);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ar_copyBtnClick_layout.setVisibility(View.VISIBLE);
                            copybtn_design.setVisibility(View.GONE);
                        }
                    }, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.eng_copyBtnClick_layout:
                try {
                    ClipboardManager engClipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData engclip = ClipData.newPlainText("", "" + coupon.code);
                    engClipboard.setPrimaryClip(engclip);
                 //   copyCodeAdjustEvent();
                    logger.logEvent("CouponCopied");
                    eng_copyBtnClick_layout.setVisibility(View.GONE);
                    copybtn_design.setVisibility(View.VISIBLE);
              //      new FadeInAnimation(copybtn_design).setDuration(700).animate();
                    coupon_copy_call(coupon);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            eng_copyBtnClick_layout.setVisibility(View.VISIBLE);
                            copybtn_design.setVisibility(View.GONE);
                        }
                    }, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.likeBtn:
                try {
                //    likeAdjustEvent();
                    logger.logEvent("CouponLiked");
                 //   showPop();
                    coupon.setLu_total("0");
                    coupon.setLu_type("2");
                  //  like_dislike_call("1", coupon, last_used_coupon_txt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.dislikeBtn:
                try {
              //      dislikeAdjustEvent();
                    logger.logEvent("CouponDisLiked");
               //     like_dislike_call("0", coupon, last_used_coupon_txt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ar_crossbtn:
            case R.id.eng_crossbtn:
                dialog.dismiss();
                break;
            case R.id.ar_sharebtn:
            //    fbGAnalyticShareCouponEvent();
                if (shareContent != null && !shareContent.isEmpty()) {
                    shareLink(coupon);
                }
                break;
            case R.id.eng_sharebtn:
                Log.e("eng", "share eng");
            //    fbGAnalyticShareCouponEvent();
                if (shareContent != null && !shareContent.isEmpty()) {
                    shareLink(coupon);
                }
                break;
        }
    }


    /**********************************************************************************/
    /*....SHARE MSG API Call...*/

    /**********************************************************************************/


    /**********************************************************************************/
    /*....Open Apps for Share Some Contents & Links....*/
    public void shareLink(CouponDetails couponDetails) {
        String shareC = shareContent;
        if (saveLanguage.equals("ar")) {
            shareC = shareC.replace("c_code", couponDetails.code);
            shareC = shareC.replace("c_dis", couponDetails.discount);
            shareC = shareC.replace("c_name", couponDetails.name);
        } else if (saveLanguage.equals("en")) {
            shareC = shareC.replace("c_code", couponDetails.code);
            shareC = shareC.replace("c_dis", couponDetails.eng_discount);
            shareC = shareC.replace("c_name", couponDetails.arabicName);
        }
        try {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sahseh APP");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareC);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share Link via:"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**********************************************************************************/


    /**********************************************************************************/
    /*....CopyCoupon API Call....*/

    /**
     *
     ********************************************************************************/
    public void coupon_copy_call(CouponDetails coupon) {
        String COUPON_COPY_URL = GetApiParameters.url + AppConstants.COPY_COUPON + coupon.getCoupon_id() +
                "&" + AppConstants.APP_UNIQUE_ID + appUniqueId;
        if (getActivity() == null) {
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, COUPON_COPY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        if (!coupon.getAdjust_event_id().equals("0")) {
                            Log.e("@AD Event API ID", "Event ID " + coupon.getAdjust_event_id());
                           // ProjectUtils.specificCouponADE(coupon.getAdjust_event_id());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    /**********************************************************************************/

    /**********************************************************************************/
    /*....Gesture Motion Event Methods Implementation....*/

    /**********************************************************************************/
    @Override
    public boolean onDown(MotionEvent e) {
        dialog.dismiss();
        isSlidingCouponVisible = false;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    /**********************************************************************************/
    /*....For Pop Messages Dialog Code Here....*/

    /**********************************************************************************/



    @Override
    public void onStop() {
        super.onStop();
        Log.e("@TAG", "onStop: called in CD Fragment");
        //CouponDetailFragment.this.dialog.dismiss();

    }

    //////*****Open PlayStore App*****/////
    public void openPlayStore() {
        Uri ruUri = Uri.parse("https://play.google.com/store/apps/details?id=com.uae.coupons");
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, ruUri);
        rateIntent.setPackage("com.android.vending");
        try {
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.uae.coupons")));
        }
    }

    public void slideToRight(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, view.getWidth(), 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        ar_copyBtnClick_layout.setVisibility(View.GONE);
        copybtn_design.setVisibility(View.VISIBLE);
    }

    /**********************************************************************************/
    /*....Swipe Down Gesture Motion Class Code....*/

    /**********************************************************************************/
    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getY() - e1.getY() > 30) {
                dialog.dismiss();
                return true;
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        /* try {
            getContext().getContentResolver().unregisterContentObserver(screenShotContentObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        appContext = null;
    }
                                    /////////////////////////////////
                                    //////Facebook Log Events///////
                                    ///////////////////////////////


    private void CouponCopied(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID);
    }
    private void CouponFav(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID);
    }
    private void CouponLiked(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID);
    }
    private void CouponDisLiked(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID);
    }
    private void CouponWebLink(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID);
    }
    private void CouponRemoveFromFav(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_NAME_SESSION_INTERRUPTIONS, orderID);
    }
}

