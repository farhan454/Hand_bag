package com.example.hand_bag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExpandView extends AppCompatActivity implements  View.OnClickListener{
    public TextView answer_text, question_txt;
    public ImageView ts_img;
    public LinearLayout main_linear_view;
    boolean click = false;
    RelativeLayout rl_color;
    CardView crd1,crd2,crd3,crd4;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;
    private Res res;
    @Override
    public Resources getResources() {
        //return super.getResources();
        if (res == null) {
            res = new Res(super.getResources(),this);

        }
        return res;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_view);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ExpandView.this);
        sharedPreferences = getSharedPreferences("api", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
        crd1=findViewById(R.id.card_1);
        crd2=findViewById(R.id.card_2);
        crd3=findViewById(R.id.card_3);
        crd4=findViewById(R.id.card_4);
        crd1.setOnClickListener(this);
        crd2.setOnClickListener(this);
        crd3.setOnClickListener(this);
        crd4.setOnClickListener(this);
        answer_text =findViewById(R.id.answer_text);
        question_txt =findViewById(R.id.question_txt);
        main_linear_view = findViewById(R.id.main_linear_view);
        ts_img = findViewById(R.id.dd_img);
        rl_color=findViewById(R.id.rl_color);
      int  resourceID = getResources().getIdentifier("api_color", "color", ExpandView.this.getPackageName());
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,resourceID));
        Log.e("@@color",""+resourceID);
        ts_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   view_line.setVisibility(View.INVISIBLE);

              //  new FadeInAnimation(main_linear_view).setDuration(500).animate();
                if (click) {
                    click = false;
                    main_linear_view.setBackgroundColor(0);
                   rl_color.setVisibility(View.GONE);
                    ts_img.setImageResource(R.drawable.down_arrow);
                    ///*** For Upper View line  visible using this logic start here***////
                    ///*** For Upper View line visible using this logic end here***////
                } else {
                    click = true;
           //         main_linear_view.setBackgroundColor(ExpandView.this.getResources().getColor(R.color.faq_select_color));
                    rl_color.setVisibility(View.VISIBLE);
               //     Animation animSlideDown = AnimationUtils.loadAnimation(ExpandView.this, R.anim.faq_slide_down);
              //      main_linear_view.startAnimation(animSlideDown);
                    ts_img.setImageResource(R.drawable.up_arrow);
                    ///*** For Upper View line visible using this logic end here***////
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_1:
                myEditor.putString("api_color", "#800080");
                myEditor.commit();
                startActivity(new Intent(ExpandView.this, DashBoardActivity.class));
                finish();
                break;
            case R.id.card_2:
                myEditor.putString("api_color", "#0000FF");
                myEditor.commit();
                startActivity(new Intent(ExpandView.this, DashBoardActivity.class));
                finish();
                break;
            case R.id.card_3:
                myEditor.putString("api_color", "#FF000000");
                myEditor.commit();
                startActivity(new Intent(ExpandView.this, DashBoardActivity.class));
                finish();
                break;
            case R.id.card_4:
                myEditor.putString("api_color", "#00FF00");
                myEditor.commit();
                startActivity(new Intent(ExpandView.this, DashBoardActivity.class));
                finish();
                break;
        }
    }
}