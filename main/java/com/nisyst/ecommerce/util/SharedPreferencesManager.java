package com.nisyst.ecommerce.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.nisyst.ecommerce.model.Users;

public class SharedPreferencesManager {

    private static SharedPreferences mSharedPref;

    synchronized public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserDetails(Users userDetail) {
        Gson gson = new Gson();
        mSharedPref.edit().putString("PREF_KEY_USER_DETAILS", gson.toJson(userDetail)).apply();
    }

    public static Users getUserDetails() {
        String json = mSharedPref.getString("PREF_KEY_USER_DETAILS", "");
        Gson gson = new Gson();
        return gson.fromJson(json, Users.class);
    }

    public static void setFCMToken(String token) {
        mSharedPref.edit().putString("PREF_KEY_USER_DETAILS_TOKEN", token).apply();
    }

    public static String getFCMToken() {
        return mSharedPref.getString("PREF_KEY_USER_DETAILS_TOKEN", "");

    }
}
