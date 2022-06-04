package com.example.hand_bag.Api;

import com.android.volley.AuthFailureError;

import java.util.Map;

public interface Callback_Headers {

    void Responce(String resp);

    Map<String, String> getHeaders() throws AuthFailureError;
}