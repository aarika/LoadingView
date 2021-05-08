package com.nisyst.ecommerce.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.nisyst.ecommerce.R;

import java.util.Objects;

public class Utility {
    public static Dialog myDialog;
    public static AlertDialog.Builder alertDialog;
    public static Dialog SpinKitProgressDialog = null;

    public static void showApiDialog(final Context context) {

        if (SpinKitProgressDialog != null) {
            if (SpinKitProgressDialog.isShowing()) {
                return;
            }
        }

        SpinKitProgressDialog = new Dialog(context);
        SpinKitProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(SpinKitProgressDialog.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        SpinKitProgressDialog.setContentView(R.layout.progress);
        SpinKitProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        SpinKitProgressDialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(SpinKitProgressDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        SpinKitProgressDialog.show();
        SpinKitProgressDialog.getWindow().setAttributes(lp);
    }

    public static void hideApiDialog() {
        if (SpinKitProgressDialog != null && SpinKitProgressDialog.isShowing()) {
            SpinKitProgressDialog.dismiss();
            SpinKitProgressDialog.cancel();
            SpinKitProgressDialog = null;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void NewIntentWithFinish(Context ourContext, Class ourClass, Boolean isFinish, Boolean isAnimation) {
        ourContext.startActivity(new Intent(ourContext, ourClass));
//        ((Activity) ourContext).overridePendingTransition((isAnimation) ? R.anim.fade_in : R.anim.animation_one,
//                (isAnimation) ? R.anim.fade_out : R.anim.animation_two);
        if (isFinish) {
            ((Activity) ourContext).finish();
        }
    }

    public static void NewIntentClearTop(Context ourContext, Class ourClass, Boolean isAnimation) {
        Intent intent = new Intent(ourContext, ourClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ourContext.startActivity(intent);
    }

    public static void showMessageInternet(final Context mContext) {
        if (myDialog != null) {
            if (myDialog.isShowing()) {
                return;
            }
        }

        alertDialog = new AlertDialog.Builder(mContext);

        TextView text = new TextView(mContext);
        text.setText(R.string.app_name);
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 40, 0, 40);
        text.setTextSize(20);
        text.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
        text.setBackgroundColor(ContextCompat.getColor(mContext, R.color.purple_500));
        alertDialog.setCustomTitle(text);

        alertDialog.setMessage("Please check your internet connection!");

        alertDialog.setPositiveButton("Settings", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            mContext.startActivity(intent);
            myDialog = null;
            dialog.dismiss();
        });

        alertDialog.setNegativeButton("Cancel", (dialogs, which) -> {
            myDialog = null;
            dialogs.dismiss();
        });

        myDialog = alertDialog.create();
        myDialog.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
