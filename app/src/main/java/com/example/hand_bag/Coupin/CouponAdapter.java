package com.example.hand_bag.Coupin;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hand_bag.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IT Empire 6/May/2020
 */
public class CouponAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    Context context;
    public String s;
    public int cc_id;
    public List<CouponDetails> listCoupon;
    public List<CouponDetails> listCouponFilteredAnimation;
    private List<CouponDetails> listCouponFiltered;
    private long mLastClickTime = System.currentTimeMillis();
    private static final long CLICK_TIME_INTERVAL = 1000;
    OnCouponClickListener onCouponClickListener;
    private SharedPreferences filterPref;
    private SharedPreferences.Editor filterPrefEditor;
    String filterCheck;
    boolean emptyList = false;
    TextView no_data_found_txt_view_id;
    String addYes = "1";
    private int mLowestPosition = -1;
  //  SharedPrefrence prefrence;
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110";
    String saveLanguage, type;

    // Type 0 means adapter call from HomeFragment All Coupon List /
    // Type 1 means adapter call from Favourite coupon list


    public CouponAdapter(Context context, List<CouponDetails> listCoupon, String type) {
        this.context = context;
        this.listCoupon = listCoupon;
        this.listCouponFiltered = listCoupon;
        this.onCouponClickListener = onCouponClickListener;
        this.listCouponFilteredAnimation = listCoupon;
        this.no_data_found_txt_view_id = no_data_found_txt_view_id;
        this.type = type;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_coupon, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        final CouponDetails coupon = listCouponFiltered.get(i);
        CustomViewHolder customViewHolder = (CustomViewHolder) viewHolder;
        customViewHolder.frame_layout.removeAllViews();
        customViewHolder.frame_layout.setVisibility(View.INVISIBLE);
        customViewHolder.lastItemLiner.setVisibility(View.VISIBLE);
        customViewHolder.tvDiscount_label.setVisibility(View.VISIBLE);
        customViewHolder.tvName.setText("" + coupon.getArabicName());
        customViewHolder.tvDiscount.setText("" + coupon.getEng_discount());
        if (context != null) {
            Glide.with(context.getApplicationContext()).load(coupon.imgUrl).placeholder(R.drawable.logo).into(customViewHolder.cvProfileImage);
        }
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("@couponClicked", "iam Clicked");
                if (customViewHolder.itemView != null) {
                    InputMethodManager imm =
                            (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(customViewHolder.itemView.getWindowToken(), 0);
                }
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                    Log.e("@couponClicked", "now clicked");

                    return;
                }
                try {
                    List<Object> objectArrayList = new ArrayList<>();
                    objectArrayList.addAll(listCouponFiltered);
                    Bundle b = new Bundle();
                    b.putSerializable("COUPON", coupon);
                    b.putString("Slide_Coupon", "1");
                    b.putInt("coupon_pos", i);
                    b.putString("type", "0");
                    b.putSerializable("Coupons_List", (Serializable) objectArrayList);
                    CouponDetailFragment detailFragment = new CouponDetailFragment();
                    detailFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
                    detailFragment.setArguments(b);
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    detailFragment.show(fm, "DialogFragment");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        /*
        ////***** This logic code for last item of recyclerview
        that behind the BottomApp Bar to show Upper side of
        the bottom Bar *****////

        if (i == getItemCount() - 1) {
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) customViewHolder.lastItemLiner.getLayoutParams();
            params1.setMargins(0, 0, 0, 100);
            customViewHolder.lastItemLiner.setLayoutParams(params1);
            //Toast.makeText(context, "Last Item", Toast.LENGTH_SHORT).show();
        } else {
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) customViewHolder.lastItemLiner.getLayoutParams();
            params1.setMargins(0, 0, 0, 0);
            customViewHolder.lastItemLiner.setLayoutParams(params1);
        }


    }

    @Override
    public int getItemCount() {
        return listCouponFiltered.size();
    }

    private Filter fRecords;

    @Override
    public Filter getFilter() {
        Log.e("@couponClicked", "iam Clicked");
        if (context != null) {
            filterPref = context.getSharedPreferences("Filter", MODE_PRIVATE);
            filterPrefEditor = filterPref.edit();
            filterCheck = filterPref.getString("filter", "");
        }
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDiscount, tvDiscount_label;
        public CircleImageView cvProfileImage;
        public LinearLayout lastItemLiner;
        //public View ar_view, eng_view;
        //AdView adView;
        public RelativeLayout cl_container;
        public FrameLayout frame_layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            lastItemLiner = (LinearLayout) itemView.findViewById(R.id.lastItemLiner);
            tvDiscount = (TextView) itemView.findViewById(R.id.tv_discount);
            tvDiscount_label = (TextView) itemView.findViewById(R.id.tvDiscount_label);
            cvProfileImage = (CircleImageView) itemView.findViewById(R.id.cv_coupon_pic);
           // ar_view = (View) itemView.findViewById(R.id.ar_view);
          //  eng_view = (View) itemView.findViewById(R.id.eng_view);
            //adView = (AdView) itemView.findViewById(R.id.adView);
            cl_container = (RelativeLayout) itemView.findViewById(R.id.cl_container);
            frame_layout = (FrameLayout) itemView.findViewById(R.id.frame_layout);

        }
    }

    public interface OnCouponClickListener {
        void onCouponClick(CouponDetails coupon, String type, int c_position);
    }

    private class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence search) {
            s = search.toString();
            FilterResults results = new FilterResults();
            if (filterCheck.equals("srh")) {
                if (s.length() == 0) {
                    listCouponFiltered = listCoupon;
                } else {
                    final List<CouponDetails> fRecords = new ArrayList<>();
                    for (CouponDetails coupon : listCoupon) {
                        ////****First Method For Matching the Exact strings ****////
                        ////*** I prefer to use Build in Method ***////
                        if ((coupon.arabicName != null && coupon.arabicName.toLowerCase().contains(s.toLowerCase()))
                                || (coupon.name != null && coupon.name.toLowerCase().contains(s.toLowerCase()))) {
                            fRecords.add(coupon);
                        }
//                        if ((coupon.arabicName != null && coupon.arabicName.toLowerCase().startsWith(s.toLowerCase()))
//                                || (coupon.name != null && coupon.name.toLowerCase().startsWith(s.toLowerCase()))) {
//                            fRecords.add(coupon);
//                        }
                        ////****Second Method For Matching the Exact strings ****////
                        ////*** Custom method to match the strings ****////
                        ////*** This is little bit slow then upper method ***////
//                            try {
//                                ///*** This condition for checking the search word length should be less or equal to coupon name***///
//                                ///*** If we not apply this condition they will give StringIndexOutOfBoundsException ***///
//                                if (s.length() <= coupon.name.length()) {
//                                    String res = coupon.name.substring(0, s.length());
//                                    if (res.equals(s)) {
//                                        fRecords.add(coupon);
//                                    }
//                                }
//                                else {
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                    }
                    listCouponFiltered = fRecords;
                    Log.e("SORT", "Before Sort Filter Data = " + listCouponFiltered.toString());
                    /*Collections.sort(listCouponFiltered, new Comparator<CouponDetails>() {
                        @Override
                        public int compare(CouponDetails o1, CouponDetails o2) {
                            Log.e("SORT NAME", "compare: =" + o1.name + "  " + o2.name);
                            return o1.name.compareToIgnoreCase(o2.name);
                        }
                    });*/
                    Log.e("SORT", "After Sort Filter Data = " + listCouponFiltered.toString());
                }
            } else if (filterCheck.equals("cat")) {
                cc_id = Integer.parseInt(s);
                Log.e("comma", "performFiltering ID:" + cc_id);
                if (cc_id == 0) {
                    listCouponFiltered = listCoupon;
                } else {
                    final List<CouponDetails> fRecords = new ArrayList<>();
                    for (CouponDetails coupon : listCoupon) {
                        if (coupon.getFk_cat_id() != null) {
                            if (coupon.getFk_cat_id().contains(",")) {
                                Log.e("comma", "Comma Values");
                                String[] splitArray = coupon.getFk_cat_id().split(",");
                                for (int i = 0; i < splitArray.length; i++) {
                                    if (splitArray[i].equals(s)) {
                                        fRecords.add(coupon);
                                    }
                                }
                            } else if (coupon.getFk_cat_id().equals(s.toLowerCase())) {
                                Log.e("comma", "Without Comma Values");
                                fRecords.add(coupon);
                            }
                        }
                    }
                    //Collections.sort();
                    listCouponFiltered = fRecords;
                    /** Filter List with category order assigned **/
                    int currentPosition = 0;
                    for (CouponDetails c : listCouponFiltered) {
                        if (c.getOrderList().length() > 0) {
                            JSONArray order_list = c.getOrderList();
                            for (int i = 0; i < order_list.length(); i++) {
                                try {
                                    JSONObject data = order_list.getJSONObject(i);
                                    String fk_cat_id = data.optString("fk_cat_id");
                                    if (fk_cat_id.equals(s)) {
                                        int order_num = data.optInt("orders");
                                        /*if (order_num < c.getOrder()) {*/
                                            c.setOrder(order_num);
                                            listCouponFiltered.set(currentPosition, c);
                                        //}
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        currentPosition++;
                    }
                    /** Sorting Coupon According Order by order assigned **/
                    Collections.sort(listCouponFiltered, new Comparator<CouponDetails>() {
                        @Override
                        public int compare(CouponDetails o1, CouponDetails o2) {
                            return o1.getOrder() - o2.getOrder(); // Ascending
                        }
                    });

                }
            }
            results.values = listCouponFiltered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.e("@res@", "publishResults: " + results);
            try {
                listCouponFiltered = (List<CouponDetails>) results.values;
                if (listCouponFiltered.size() <= 0) {
                    no_data_found_txt_view_id.setVisibility(View.VISIBLE);
                } else {
                    no_data_found_txt_view_id.setVisibility(View.GONE);
                }
                notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**Filter Logic**/

    /*public void animateTo(List<CouponDetails> models ) {

        applyAndAnimateRemovals( models );

        applyAndAnimateAdditions( models );

        applyAndAnimateMovedItems( models );
    }

    private void applyAndAnimateRemovals(List<CouponDetails> newModels ) {

        for (int i = listCoupon .size() - 1; i >= 0; i--) {

            final CouponDetails model = listCoupon .get( i );

            if (! newModels .contains( model )) {

                removeItem( i );

            }

        }

    }

    private void applyAndAnimateAdditions(List<CouponDetails> newModels ) {

        for (int i = 0, count = newModels .size(); i < count ; i++) {

            final CouponDetails model = newModels .get(i);

            if (! listCoupon .contains( model )) {

                addItem( i , model );

            }

        }

    }

    private void applyAndAnimateMovedItems(List<CouponDetails> newModels ) {

        for (int toPosition = newModels .size() - 1; toPosition >= 0; toPosition-- ) {

            final CouponDetails model = newModels .get( toPosition );

            final int fromPosition = listCoupon .indexOf( model );

            if ( fromPosition >= 0 && fromPosition != toPosition ) {

                moveItem( fromPosition , toPosition );

            }

        }

    }

    public CouponDetails removeItem(int position ) {

        final CouponDetails model = listCoupon.remove( position );

        notifyItemRemoved( position );

        return model ;

    }

    public void addItem(int position , CouponDetails model ) {

        listCoupon .add( position , model );

        notifyItemInserted( position );

    }

    public void moveItem(int fromPosition , int toPosition ) {

        final CouponDetails model = listCoupon .remove( fromPosition );

        listCoupon .add( toPosition , model );

        notifyItemMoved( fromPosition , toPosition );

    }*/

}


