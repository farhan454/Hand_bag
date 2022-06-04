package com.example.hand_bag.Coupin;

import android.content.Context;

public class GetApiParameters {

    /**
     * You must set url & Base url empty when go to Production
     */

    /**when enable dynamic url api call these empty url **/
  /*  public static String url = "";
    public static String baseUrl = "";
    public  static  String newUrl= "";*/
 /*   public static String   url = "https://new.sahseh.co/api/";
    public  static  String newUrl= "https://new.sahseh.co/";
    public static String baseUrl = "";*/

    //Beta testing url

    public static String url = "https://beta.sahseh.co/api/";
    public  static  String newUrl= "https://beta.sahseh.co/";
    public static String baseUrl = "";

    //staging url
   /* public static String url = "https://staging.sahseh.co/api/";
    public static String baseUrl = "";
    public  static  String newUrl= "https://staging.sahseh.co/";*/
    public GetApiParameters(Context context) {

    }

    public static String getUrl() {
        return url;
    }
    public static String getNewUrl() {
        return newUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
