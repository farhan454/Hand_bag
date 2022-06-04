package com.example.hand_bag.Coupin;

public class AppConstants {
    /*
    Sign In Flow in App Explanation
    I have used type open_from (0,1,2,3....)
    0 = Its means user open login Error POP UP in page from Home Button
    1 = Its means user open login Error POP UP in page from Main Menu
    2 = Its means user open login Error POP UP in page from Coupon Detail when press Favorite Icon
    3 = In Future we used this......
    */
   // public static final String LIVE_LINK_URL = "https://sahseh.co/env.php?dev";
    public static final String LIVE_LINK_URL = "https://sahseh.co/env.php";
    public static final String IS_LOGIN = "is_login";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASS = "user_pass";
    public static final String USER_TYPE = "user_type";
    public static final String UNSIGNED_USER = "unsigned_user";
    public static final String SIGNED_UP_USER = "signed_up_user";
    public static final String SIGNED_IN_USER = "signed_in_user";
    public static final String USER_AGE = "user_age";
    public static final String USER_COUNTRY = "user_country";
    public static final String USER_ADD_COUPON = "user_added_coupon";
    /* REST API's params Constants */
    public static final String NEW_IN_COUPON_CLICK = "new_in_coupon_click";
    public static final String NEW_IN_COUPON = "new_in_coupons_list";
    public static final String ALL_COUPON_LIST = "all_coupons_list?fk_country_id=";
    public static final String ALL_COUPON_LIST_WITH_ADS = "all_coupons_list?ad=1&?1fk_country_id=";
    public static final String CATEGORY_LIST = "categories_list";
    public static final String SLIDER_LIST = "sliders_list";
    public static final String SLIDER_LIST_WITH_ADS = "sliders_list?ad=1";
    public static final String SLIDER_CLICK_API = "total_count?type=0&id="; // type=0 means slider click
    public static final String CATEGORY_CLICK_API = "total_count?type=1&id="; // type=1 means category click
    public static final String LINK_CLICK_API = "total_count?type=2&coupens_id="; // type=2 means link click
    public static final String USER_DETAIL = "user_detail?id=";
    public static final String FAQS_LIST = "faqs_list";
    public static final String EDIT_USER = "edit_user";
    public static final String ADD_OPINION = "add_opinion?";
    public static final String USER_COUPON_LIST = "user_coupons?fk_user_id=";
    public static final String ADD_COUPON = "add_coupons--?";
    public static final String LIKE_DISLIKE_COUPON = "like_coupens--?&coupens_id--=";
    public static final String COPY_COUPON = "copy_code--?&coupens_id--=";
    public static final String APP_UNIQUE_ID = "appUniqueId=";
    public static final String ADD_RATING = "add_rating?";
    public static final String SHARE_MSG = "share_msg?";
    public static final String ADD_FAV_COUPON = "add_to_favourite";
    public static final String FAV_COUPON_LIST = "all_coupons_list_favourite?fk_country_id=";
    public static final String COUNTRIES_LIST = "countries_list";
    public static final String UPDATE_COUNTRY = "update_user_country";
    /* Google Analytics Event Constants */
    public static final String FAVORITE_COUPON_COUNT_ANDROID = "favorite_coupon_count_android";
    public static final String SCROLL_COUPON_PAGE_ANDROID = "scroll_coupon_page_android";
    public static final String Horizontal_SCROLL_COUPON_PAGE_ANDROID = "horizontal_sliding_android";

}
