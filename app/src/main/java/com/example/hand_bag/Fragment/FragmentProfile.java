package com.example.hand_bag.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import android.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.hand_bag.DashBoardActivity;
import com.example.hand_bag.ExpandView;
import com.example.hand_bag.R;
import com.example.hand_bag.Res;
import com.example.hand_bag.users.EditProfileScreen;
import com.example.hand_bag.users.SignUpActivity;


public class FragmentProfile extends Fragment implements View.OnClickListener{
ImageView img_edit;
LinearLayout ll_setting;
    int resourceID;
    private Res res;
    LinearLayout ll_top,ll_payment,ll_tellfrend,ll_promotion,ll_logout,ll_settings,ll_themes,ll_themess;
    TextView txt_cart,txt_heart,txt_credit;
    ImageView img_cart,img_heart,img_credit;
    RelativeLayout rl_color;
    boolean click = false;
    CardView crd1,crd2,crd3,crd4;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        img_edit=view.findViewById(R.id.img_edit);
        ll_setting=view.findViewById(R.id.ll_setting);
        ll_top=view.findViewById(R.id.ll_top);
        ll_payment=view.findViewById(R.id.ll_paymnet_history);
        ll_tellfrend=view.findViewById(R.id.ll_tellyour_frend);
        ll_promotion=view.findViewById(R.id.ll_promotion);
        ll_logout=view.findViewById(R.id.ll_logout);
        txt_cart=view.findViewById(R.id.txt_cart);
        txt_heart=view.findViewById(R.id.txt_heart);
        txt_credit=view.findViewById(R.id.txt_credit);
        img_cart=view.findViewById(R.id.img_cart);
        img_heart=view.findViewById(R.id.img_heart);
        img_credit=view.findViewById(R.id.img_credit);
        ll_settings=view.findViewById(R.id.ll_settings);
        ll_themes=view.findViewById(R.id.ll_theme);
        ll_themess=view.findViewById(R.id.ll_themess);
        rl_color=view.findViewById(R.id.rl_color);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences = getActivity().getSharedPreferences("api", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
        crd1=view.findViewById(R.id.card_1_);
        crd2=view.findViewById(R.id.card_2_);
        crd3=view.findViewById(R.id.card_3_);
        crd4=view.findViewById(R.id.card_4_);
        crd1.setOnClickListener(this);
        crd2.setOnClickListener(this);
        crd3.setOnClickListener(this);
        crd4.setOnClickListener(this);

        resourceID =view. getResources().getIdentifier("api_color", "color", getContext().getPackageName());
        Log.e("@@color",""+resourceID);
        ll_top.setBackgroundColor(getResources().getColor(resourceID));
        ll_payment.setBackgroundColor(getResources().getColor(resourceID));
        ll_tellfrend.setBackgroundColor(getResources().getColor(resourceID));
        ll_promotion.setBackgroundColor(getResources().getColor(resourceID));
        ll_logout.setBackgroundColor(getResources().getColor(resourceID));
        ll_settings.setBackgroundColor(getResources().getColor(resourceID));
        txt_credit.setTextColor(getResources().getColor(resourceID));
        txt_heart.setTextColor(getResources().getColor(resourceID));
        txt_cart.setTextColor(getResources().getColor(resourceID));
        img_heart.setColorFilter(ContextCompat.getColor(getContext(), resourceID));
        img_credit.setColorFilter(ContextCompat.getColor(getContext(), resourceID));
        img_cart.setColorFilter(ContextCompat.getColor(getContext(), resourceID));
        ll_themess.setBackgroundColor(getResources().getColor(resourceID));
        ll_themes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    click = false;
                    rl_color.setVisibility(View.GONE);
                 //   ts_img.setImageResource(R.drawable.down_arrow);
                    ///*** For Upper View line  visible using this logic start here***////
                    ///*** For Upper View line visible using this logic end here***////
                } else {
                    click = true;
                    //         main_linear_view.setBackgroundColor(ExpandView.this.getResources().getColor(R.color.faq_select_color));
                    rl_color.setVisibility(View.VISIBLE);
                        Animation animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.faq_slide_down);
                          rl_color.startAnimation(animSlideDown);
            //        ts_img.setImageResource(R.drawable.up_arrow);
                    ///*** For Upper View line visible using this logic end here***////
                }
            }
        });




        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ExpandView.class));
            }
        });
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditProfileScreen.class));
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_1_:
                myEditor.putString("api_color", "#800080");
                myEditor.commit();
                Intent intent = getActivity().getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);

                break;
            case R.id.card_2_:
                myEditor.putString("api_color", "#0000FF");
                myEditor.commit();
                Intent intents = getActivity().getIntent();
                intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intents);


                break;
            case R.id.card_3_:
                myEditor.putString("api_color", "#FF000000");
                myEditor.commit();
                startActivity(new Intent(getContext(), DashBoardActivity.class));
                Intent intentss = getActivity().getIntent();
                intentss.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intentss);

                break;
            case R.id.card_4_:
                myEditor.putString("api_color", "#00FF00");
                myEditor.commit();
                startActivity(new Intent(getContext(), DashBoardActivity.class));
                Intent intentsss = getActivity().getIntent();
                intentsss.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intentsss);

                break;
        }
    }
}