package com.example.hand_bag;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

public class Res extends Resources {
    private Context context;
    public SharedPreferences.Editor myEditor;
    public  SharedPreferences sharedPreferences;

    public Res(Resources original, android.content.Context context) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
        this.context = context;
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        return getColor(id, null);
    }

    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences = context.getSharedPreferences("api", Context.MODE_PRIVATE);
        myEditor = sharedPreferences.edit();
        switch (getResourceEntryName(id)) {
            case "api_color":
                String savedData= sharedPreferences.getString("api_color", "");
                Log.e("@@rescolor",savedData);
                Log.e("@SavedValue", savedData);
                Color.parseColor(savedData);
                return Color.parseColor(savedData);
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return super.getColor(id, theme);
                }
                // return super.getColor(id,theme);
                return getColor(id,theme);
        }
    }
}
