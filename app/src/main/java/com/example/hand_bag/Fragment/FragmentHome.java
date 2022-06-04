package com.example.hand_bag.Fragment;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.hand_bag.HelperClass.SliderAdapter;
import com.example.hand_bag.HelperClass.SliderData;
import com.example.hand_bag.HelperClass.TextAdapter;
import com.example.hand_bag.HelperClass.TxtRecycleHelperClass;
import com.example.hand_bag.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    MeowBottomNavigation bottomNavigation;
    Fragment fragment=null;
    // Urls of our images.

    TextAdapter adapter;
    List<TxtRecycleHelperClass> list;
    private ProgressDialog progressDialog;
    RecyclerView recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();
        recyclerview = view.findViewById(R.id.horizantal);
        adapter = new TextAdapter(getContext(), list);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerview.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(R.drawable.slider_full));
        sliderDataArrayList.add(new SliderData(R.drawable.slider_full2));
        sliderDataArrayList.add(new SliderData(R.drawable.slider_full3));
        sliderDataArrayList.add(new SliderData(R.drawable.slider_full4));
        sliderDataArrayList.add(new SliderData(R.drawable.slider_full5));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        data();
        fragment= new FragmentBags();
        loadFragment(fragment,"bag");




        return view;
    }
    private void data() {
        TxtRecycleHelperClass txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);
        txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);
        txtRecycleHelperClass=new TxtRecycleHelperClass("Bags");
        list.add(txtRecycleHelperClass);
        txtRecycleHelperClass=new TxtRecycleHelperClass("Shoes");
        list.add(txtRecycleHelperClass);txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);
        txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);
        txtRecycleHelperClass=new TxtRecycleHelperClass("Clothes");
        list.add(txtRecycleHelperClass);
        adapter.notifyDataSetChanged();





    }
    private void loadFragment(Fragment fragment,String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);

        //Prevent adding same fragment on top
//        if (currentFragment.getClass() == fragment.getClass()) {
//            return;
//        }

        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (getFragmentManager().findFragmentByTag(tag) != null) {
            getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.frame_container, fragment, tag)
                .commit();
    }
}