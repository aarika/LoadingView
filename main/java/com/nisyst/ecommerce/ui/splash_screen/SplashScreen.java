package com.nisyst.ecommerce.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nisyst.ecommerce.ui.main.MainActivity;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.ui.auth.LoginScreen;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nisyst.ecommerce.util.Utility.NewIntentClearTop;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.lottieView)
    LottieAnimationView lottieView;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        initView();

        loadAnimation();
    }

    private void initView() {
        try {
            mAuth = FirebaseAuth.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadAnimation() {
        try {
            lottieView.setAnimation("ecommerce.json");
            lottieView.playAnimation();
            lottieView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mCurrentUser = mAuth.getCurrentUser();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    jumpToNextScreen();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpToNextScreen() {
        if (mCurrentUser == null) {
            NewIntentClearTop(this, LoginScreen.class, true);
        } else {
            NewIntentClearTop(this, MainActivity.class, true);
        }
    }
}