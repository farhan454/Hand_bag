package com.example.hand_bag.Fragment;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.example.hand_bag.R;


public class FragmentFavourite extends Fragment {
  CardView crd_1,crd_2,crd_3,crd_4;
  ImageView img_1,img_2,img_3,img_4;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        crd_1=view.findViewById(R.id.crd_1);
        crd_2=view.findViewById(R.id.card_2);
        crd_3=view.findViewById(R.id.card_3);
        crd_4=view.findViewById(R.id.card_4);
        img_1=view.findViewById(R.id.img_heart_1);
        img_2=view.findViewById(R.id.img_heart_2);
        img_3=view.findViewById(R.id.img_heart_3);
        img_4=view.findViewById(R.id.img_heart_4);
        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crd_1.setVisibility(View.GONE);
            }
        });
        img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crd_2.setVisibility(View.GONE);
            }
        });
        img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crd_3.setVisibility(View.GONE);
            }
        });
        img_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crd_4.setVisibility(View.GONE);
            }
        });
        return view;
    }
}