package com.example.hand_bag.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hand_bag.R;


public class FragmentCart extends Fragment {

    RelativeLayout relativeLayout;
    public SharedPreferences.Editor myEditor;
    SharedPreferences sharedpreferences;
    TextView txt_des;
    TextView txt_price;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
/*        sharedpreferences =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String quantitiy  =sharedpreferences.getString("quantity","");
        relativeLayout=view.findViewById(R.id.RL_layout);
        txt_des=view.findViewById(R.id.items);
        txt_price=view.findViewById(R.id.txt_price) ;
        int fix_price= 222;
        Double price_fix = Double.parseDouble(String.valueOf(fix_price));
        Double items = Double.parseDouble(quantitiy);
        Double total_price = items * price_fix;
        txt_des.setText(   "Toal Items :"+  quantitiy);
        txt_price.setText(  ""+ total_price);*/

        return view;
    }
}