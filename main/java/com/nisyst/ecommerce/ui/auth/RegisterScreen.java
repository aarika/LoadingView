package com.nisyst.ecommerce.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nisyst.ecommerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nisyst.ecommerce.util.Utility.NewIntentClearTop;
import static com.nisyst.ecommerce.util.Utility.hideApiDialog;
import static com.nisyst.ecommerce.util.Utility.isNetworkAvailable;
import static com.nisyst.ecommerce.util.Utility.isValidEmail;
import static com.nisyst.ecommerce.util.Utility.showApiDialog;
import static com.nisyst.ecommerce.util.Utility.showMessageInternet;
import static com.nisyst.ecommerce.util.Utility.showToast;

public class RegisterScreen extends AppCompatActivity {

    @BindView(R.id.tilRegUsername)
    TextInputLayout tilRegUsername;
    @BindView(R.id.txtRegUsername)
    TextInputEditText txtRegUsername;
    @BindView(R.id.tilRegPassword)
    TextInputLayout tilRegPassword;
    @BindView(R.id.txtRegPassword)
    TextInputEditText txtRegPassword;

    String strUser, strPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        try {
            mAuth = FirebaseAuth.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValid() {

        if (TextUtils.isEmpty(txtRegUsername.getText().toString())) {
            tilRegUsername.setError("Username Required !!!");
        } else {
            if (isValidEmail(txtRegUsername.getText().toString())) {
                tilRegUsername.setError(null);
                strUser = txtRegUsername.getText().toString().trim();
            } else {
                tilRegUsername.setError("Invalid Username Required !!!");
            }
        }

        if (TextUtils.isEmpty(txtRegPassword.getText().toString())) {
            tilRegPassword.setError("Password Required !!!");
        } else {
            tilRegPassword.setError(null);
            strPass = txtRegPassword.getText().toString().trim();
            if (strPass.length() >= 6) {
                tilRegPassword.setError(null);
            } else {
                tilRegPassword.setError("Password should have minimum 6 char !!!");
            }
        }

        if (tilRegUsername.isErrorEnabled() || tilRegPassword.isErrorEnabled()) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.btnSignUp, R.id.lblSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                if (isValid()) {
                    setRegister();
                }
                break;

            case R.id.lblSignIn:
                NewIntentClearTop(RegisterScreen.this, LoginScreen.class, true);
                break;
        }
    }

    private void setRegister() {
        showApiDialog(this);
        if (isNetworkAvailable(this)) {
            mAuth.createUserWithEmailAndPassword(strUser, strPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideApiDialog();
                            if (task.isSuccessful()) {
                                NewIntentClearTop(RegisterScreen.this, ProfileDataScreen.class, true);
                            } else {
                                showToast(RegisterScreen.this, "Authentication failed.");
                            }
                        }
                    });
        } else {
            showMessageInternet(this);
        }
    }
}