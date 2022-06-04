package com.example.hand_bag.DesignScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.hand_bag.R;

public class DesignMain extends AppCompatActivity {
RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_main);
        relativeLayout= findViewById(R.id.rl_main);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DesignMain.this, DesignRecent.class));
            }
        });
    }
}