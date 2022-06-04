package com.example.hand_bag.Coupin;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import com.example.hand_bag.HelperClass.BagsHelperClass;
import com.example.hand_bag.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by IT Empire 6/May/2020
 */
public class SlideCouponPagerAdapter extends PagerAdapter {

    Context context;
    public String s;
    // A menu item view type.
    private List<Object> mFilterRecyclerViewItems;
    Dialog dialog;
    String saveLanguage, type;
    String shareContent;
    GestureDetector gestureDetector;
    CouponDetailFragment fragment;
    String appUniqueId;
    String link_text;
    private static final String LINK_TEXT_KEY = "link_text";
    ViewPager viewPager;
    boolean not_logged_in;


    public SlideCouponPagerAdapter(Context context, List<Object> listCoupon, Dialog dialog, ViewPager viewPager, String type) {
        Log.e("@@cons","call");
        this.context = context;
        this.mFilterRecyclerViewItems = listCoupon;
        this.dialog = dialog;
        appUniqueId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        this.viewPager = viewPager;
        this.type = type;

    }

    /**
     * Return the number of views available.
     */
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
        try {
            final BagsHelperClass baga = (BagsHelperClass) mFilterRecyclerViewItems.get(position);

//            final CouponDetails coupon = (CouponDetails) mFilterRecyclerViewItems.get(position);
        //    Log.e("@Ck@", "last valid use value = " + coupon.lu_total);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sliding_view, container, false);
            v.setTag("View" + position);





            container.addView(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    private void copyMethod(String code, LinearLayout copyBtnClick_layout, RelativeLayout copybtn_design) {
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", "" + code);
            clipboard.setPrimaryClip(clip);
            copyBtnClick_layout.setVisibility(View.GONE);
            copybtn_design.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    copyBtnClick_layout.setVisibility(View.VISIBLE);
                    copybtn_design.setVisibility(View.GONE);
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}


