package com.nisyst.ecommerce.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.ui.auth.LoginScreen;
import com.nisyst.ecommerce.ui.main.admin.AdminScreen;
import com.nisyst.ecommerce.ui.main.client.ClientScreen;
import com.nisyst.ecommerce.util.SharedPreferencesManager;
import com.nisyst.ecommerce.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nisyst.ecommerce.util.Utility.NewIntentClearTop;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPreferencesManager.getUserDetails().getIsAdmin() == 1) {
            NewIntentClearTop(this, AdminScreen.class, true);
        } else {
            NewIntentClearTop(this, ClientScreen.class, true);
        }
    }
}