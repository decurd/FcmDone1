package com.decurd.fcm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by decurd on 2017-05-29.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "fcmsharedprefdemo";
    private static final String KEY_ACCESS_TOKEN = "token";

    private static Context mContext;
    private static SharedPrefManager mInstance;

    public SharedPrefManager(Context context) {
        mContext = context;
    }

    public static synchronized SharedPrefManager getInstace(Context context) {
        if (mInstance == null)
            mInstance = new SharedPrefManager(context);

        return mInstance;
    }

    public boolean storeToken(String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }
}
