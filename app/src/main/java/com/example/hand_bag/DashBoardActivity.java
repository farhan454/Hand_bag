package com.example.hand_bag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.hand_bag.Coupin.HomeFragments;
import com.example.hand_bag.Fragment.FragmentCart;
import com.example.hand_bag.Fragment.FragmentFavourite;
import com.example.hand_bag.Fragment.FragmentHome;
import com.example.hand_bag.Fragment.FragmentProfile;
import com.example.hand_bag.HelperClass.SliderAdapter;
import com.example.hand_bag.HelperClass.SliderData;
import com.example.hand_bag.HelperClass.TextAdapter;
import com.example.hand_bag.HelperClass.TxtRecycleHelperClass;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity  {
    MeowBottomNavigation bottomNavigation;
    Fragment fragment=null;
    FrameLayout frame_container;

    AppEventsLogger logger;
    int resourceID;
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
        setContentView(R.layout.activity_dash_board);
        logger = AppEventsLogger.newLogger(this);
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        Purchased("");
         resourceID = getResources().getIdentifier("api_color", "color", DashBoardActivity.this.getPackageName());
        Log.e("@@color",""+resourceID);
        bottomNavigation = findViewById(R.id.bottom_bar);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_shopping_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_favorite_border_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profile));
        bottomNavigation.setCircleColor(getResources().getColor(resourceID));
        bottomNavigation.setDefaultIconColor(getResources().getColor(resourceID));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        fragment= new HomeFragments();
                        loadFragment(fragment,"home");
                        break;
                    case 2:
                        fragment= new FragmentCart();
                        loadFragment(fragment,"cart");
                        logger.logEvent("Purchased");
                        break;
                    case 3:
                        fragment= new FragmentFavourite();
                        loadFragment(fragment,"favrt");
                        break;
                    case 4:
                        fragment= new FragmentProfile();
                        loadFragment(fragment,"profile");
                        break;
                    default:
                        fragment= new FragmentHome();
                        loadFragment(fragment,"home");
                        break;

                }
                // your codes
            }
        });
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,resourceID));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                // your codes
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
       bottomNavigation.show(1,true);
        fragment= new HomeFragments();
        loadFragment(fragment,"home");
    }

    private void loadFragment(Fragment fragment,String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);

        //Prevent adding same fragment on top
//        if (currentFragment.getClass() == fragment.getClass()) {
//            return;
//        }

        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
//        if (getFragmentManager().findFragmentByTag(tag) != null) {
//            getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.frame_container_dash, fragment, tag)
                .commit();
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment =      getFragmentManager().findFragmentById(R.id.frame_container_dash);

        /// Toast.makeText(BreadActivity.this, "clicked item : "+currentFragment , Toast.LENGTH_SHORT).show();
        //  Fragment hfrag= new Home_Fragment();
        if (currentFragment  instanceof HomeFragments ) {
            //     logout.setVisibility(View.GONE);
            // Toast.makeText(BreadActivity.this, "clicked item : "+currentFragment , Toast.LENGTH_SHORT).show();
            finish();
        } else {
       //     bottomNavigation.show(1,true);
            fragment=new HomeFragments();
            loadFragment(fragment,"home");

        }
//            int fragmentsInStack = getFragmentManager().getBackStackEntryCount();
////    Toast.makeText(BreadActivity.this, "clicked item : " + fragmentsInStack, Toast.LENGTH_SHORT).show();
//    if (fragmentsInStack > 2) { // If we have more than one fragment, pop back stack
//        getFragmentManager().popBackStack();
//
//
//    } else if (fragmentsInStack == 1) {
//        // Finish activity, if only one fragment left, to prevent leaving empty screen
//
//        finish();
//    } else {
//        super.onBackPressed();
//    }
    }
    private void Purchased(String orderID) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderID );
    }
}