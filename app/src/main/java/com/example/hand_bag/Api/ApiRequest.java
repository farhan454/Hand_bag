package com.example.hand_bag.Api;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiRequest {

    public static void Call_Api(int post, final Context context, String url, JSONObject jsonObject,
                                final Callback callback) {
        Log.d("Giftoria_Log", url);
        Log.d("GGO_Log", jsonObject.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(post,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("GGO_Log_response", response.toString());

                        if (callback != null) {
                            try {
                                callback.Responce(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GGO_log_error", error.toString() +error.getMessage());
                error.printStackTrace();


                if (callback != null) {
                    try {
                        callback.Responce(error.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjReq);
    }


    public static void CAll_API_StringRequest(int post, final Context context, String url, final Callback callback) {
        StringRequest postRequest = new StringRequest(post, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        Log.d("GGO_Log_response", response.toString());

                        if (callback != null) {
                            try {
                                callback.Responce(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("GGO_log_error", error.toString());

                        if (callback != null) {
                            try {
                                callback.Responce(error.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public static void Call_Api_Header(int post, final Context context, String url, JSONObject jsonObject,
                                       final Callback_Headers callback) {
        Log.d("Giftoria_Log", url);
        Log.d("GGO_Log", jsonObject.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(post,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("GGO_Log_response", response.toString());

                        if (callback != null)
                            callback.Responce(response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Giftoria_log_error", error.toString());

                if (callback != null)
                    callback.Responce(error.toString());

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

}

