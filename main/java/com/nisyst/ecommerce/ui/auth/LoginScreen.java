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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nisyst.ecommerce.ui.main.MainActivity;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.model.Users;
import com.nisyst.ecommerce.util.SharedPreferencesManager;

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

public class LoginScreen extends AppCompatActivity {

    @BindView(R.id.tilUsername)
    TextInputLayout tilUsername;
    @BindView(R.id.txtUsername)
    TextInputEditText txtUsername;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.txtPassword)
    TextInputEditText txtPassword;

    String strUser, strPass;

    private FirebaseAuth mAuth;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
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

        if (TextUtils.isEmpty(txtUsername.getText().toString())) {
            tilUsername.setError("Username Required !!!");
        } else {
            if (isValidEmail(txtUsername.getText().toString())) {
                tilUsername.setError(null);
                strUser = txtUsername.getText().toString().trim();
            } else {
                tilUsername.setError("Invalid Username Required !!!");
            }
        }

        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            tilPassword.setError("Password Required !!!");
        } else {
            strPass = txtPassword.getText().toString().trim();
            if (strPass.length() >= 6) {
                tilPassword.setError(null);
            } else {
                tilPassword.setError("Password should have minimum 6 char !!!");
            }
        }

        if (tilUsername.isErrorEnabled() || tilPassword.isErrorEnabled()) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.btnLogIn, R.id.lblSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogIn:
                if (isValid()) {
                    setLogin();
                }
                break;

            case R.id.lblSignUp:
                NewIntentClearTop(LoginScreen.this, RegisterScreen.class, true);
                break;
        }
    }

    private void setLogin() {
        showApiDialog(this);
        if (isNetworkAvailable(this)) {
            mAuth.signInWithEmailAndPassword(strUser, strPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideApiDialog();
                            if (task.isSuccessful()) {
                                fetchUserDetails();
                            } else {
                                showToast(LoginScreen.this, "Authentication failed.");
                            }
                        }
                    });
        } else {
            showMessageInternet(this);
        }
    }

    private void fetchUserDetails() {
        showApiDialog(this);
        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mDatabaseReference.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                SharedPreferencesManager.setUserDetails(users);
                hideApiDialog();
                NewIntentClearTop(LoginScreen.this, MainActivity.class, true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                hideApiDialog();
                showToast(LoginScreen.this, "Something went wrong.");
            }
        });
    }

}