package com.nisyst.ecommerce.apps;

import android.app.Application;

import com.nisyst.ecommerce.util.SharedPreferencesManager;

public class GlobalApps extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
    }
}
