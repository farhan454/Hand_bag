package com.example.hand_bag.Coupin;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentResolver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.hand_bag.Api.ApiRequest;
import com.example.hand_bag.Api.Callback;
import com.example.hand_bag.ExpandView;
import com.example.hand_bag.R;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.protobuf.Api;
import com.google.zxing.oned.OneDimensionalCodeWriter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragments extends Fragment {
    RecyclerView rvCoupon;
    CouponAdapter couponAdapter;
    List<CouponDetails> list;
    MeowBottomNavigation bottomNavigation;
    private LinearLayoutManager linearLayoutManager;
    public HomeFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.fragment_home_fragments, container, false);
        list = new ArrayList<>();
        bottomNavigation = getActivity().findViewById(R.id.bottom_bar);
        rvCoupon = view.findViewById(R.id.recyclerView);
        couponAdapter = new CouponAdapter(getActivity(), list, "0");
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCoupon.setLayoutManager(linearLayoutManager);
        rvCoupon.setAdapter(couponAdapter);
        rvCoupon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("-----", "end");


                }

            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && bottomNavigation.isShown()) {
                    slideDown(bottomNavigation);
                    bottomNavigation.setVisibility(View.GONE);

                } else if (dy < 0) {
                    bottomNavigation.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.faq_slide_up);
                    bottomNavigation.startAnimation(animSlideDown);
                }


                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                Log.e("TAG", "onScrolled: adapter position = " + manager.findLastCompletelyVisibleItemPosition());
            }
        });

        get_Limited_Coupons_List();
        api();
        return view;
    }

    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                100,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(2000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    private  void api(){
       String ALL_COUPON_URL = GetApiParameters.url + AppConstants.ALL_COUPON_LIST + "1" + "&limit=30";
        ApiRequest.CAll_API_StringRequest(Request.Method.POST, getContext(), ALL_COUPON_URL, new Callback() {
            @Override
            public void Responce(String resp) throws JSONException {
                Log.d("@@res_generic",resp);
            }
        } );
    }
    public void get_Limited_Coupons_List() {
        String ALL_COUPON_URL;

        ALL_COUPON_URL = GetApiParameters.url + AppConstants.ALL_COUPON_LIST + "1" + "&limit=30";
        StringRequest limited_coupon_list_req = new StringRequest(Request.Method.POST, ALL_COUPON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("@@Data@@", response.toString());
                //   list.clear();
                try {
                    if (response.equals("0") || response.equals("null")) {

                        //  is_recycler_list_scroll_finished = true;

                    } else {
                        JSONArray data = new JSONArray(response);
                        for (int i = 0; i < data.length(); i++) {
                            String last_used = "", lu_type = "", lu_total = "";
                            JSONObject subCategory = data.getJSONObject(i);
                            String coupens_id = subCategory.getString("coupens_id");
                            String arabicName = subCategory.getString("arabicName");
                            String name = subCategory.getString("name");
                            String code = subCategory.getString("code");
                            Long codeCount = subCategory.getLong("codeCount");
                            String details = subCategory.getString("details");
                            String eng_details = subCategory.getString("eng_details");
                            String eng_discount = subCategory.getString("eng_discount");
                            String discount = subCategory.getString("discount");
                            String website = subCategory.getString("website");
                            String fk_cat_id = subCategory.getString("fk_cat_id");
                            String imgUrl = subCategory.getString("imgUrl");
                            String brief = subCategory.optString("brief");
                            String eng_brief = subCategory.optString("eng_brief");
                            String favourite = subCategory.optString("favourite");
                            String adjust_event_id = subCategory.optString("adjust_id");
                            int order_number = subCategory.getInt("order");
                            JSONArray order_list = subCategory.getJSONArray("orders");
                            if (!imgUrl.equalsIgnoreCase("")) {
                                imgUrl = GetApiParameters.baseUrl + imgUrl;
                            }
                            last_used = subCategory.optString("last_used");
                            if (!last_used.equalsIgnoreCase("")) {
                                JSONObject object = new JSONObject(last_used);
                                //Log.e("@LU@", "onResponse lu coupon = "+object.toString());
                                lu_type = object.optString("type");
                                lu_total = object.optString("total");
                            } else {
                                //Log.e("@LU@", "onResponse lu coupon empty !!!!!!!!!!!! "+last_used);
                            }
                            if (arabicName.equals("android_advertisement") || name.equals("android_advertisement") ||
                                    arabicName.equals("advertisement") || name.equals("advertisement")) {
                                //Log.e("@CADS@", "Ads added to List = "+mRecyclerViewItems);
                            } else {
                                list.add(new CouponDetails(arabicName, code, codeCount, details,
                                        discount, coupens_id, imgUrl, name, website, fk_cat_id, eng_details, eng_discount, lu_type, lu_total, brief, eng_brief, favourite, adjust_event_id, order_number, order_list));
                            }
                        }
                        Log.e("@@size_of_list", String.valueOf(list.size()));
                        couponAdapter.notifyDataSetChanged();


                        //     is_recycler_list_scroll_finished = true;

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getActivity() == null) {
                    return;

                }

            }
        });
        limited_coupon_list_req.setRetryPolicy(new DefaultRetryPolicy(
                0,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getContext()).addToRequestQueue(limited_coupon_list_req);
    }

}