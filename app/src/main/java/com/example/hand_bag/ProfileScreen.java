package com.example.hand_bag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ProfileScreen extends AppCompatActivity {
RelativeLayout relativeLayout;
    LinearLayout logout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        relativeLayout=findViewById(R.id.relatives_ll);
        logout_btn=findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
                Log.e("@@loginG", "exit");
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ghraphs.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}