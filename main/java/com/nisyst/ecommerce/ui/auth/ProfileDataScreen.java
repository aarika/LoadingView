package com.nisyst.ecommerce.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import static com.nisyst.ecommerce.util.Utility.showApiDialog;
import static com.nisyst.ecommerce.util.Utility.showMessageInternet;
import static com.nisyst.ecommerce.util.Utility.showToast;

public class ProfileDataScreen extends AppCompatActivity {

    @BindView(R.id.tilFirstName)
    TextInputLayout tilFirstName;
    @BindView(R.id.txtFirstName)
    TextInputEditText txtFirstName;
    @BindView(R.id.tilLastName)
    TextInputLayout tilLastName;
    @BindView(R.id.txtLastName)
    TextInputEditText txtLastName;
    @BindView(R.id.tilMobile)
    TextInputLayout tilMobile;
    @BindView(R.id.txtMobile)
    TextInputEditText txtMobile;
    @BindView(R.id.switchIsAdmin)
    SwitchCompat switchIsAdmin;

    String strFirstName, strLastName, strMobile;
    int isAdmin = 0;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    DatabaseReference mDatabaseReference;

    Users saveUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_screen);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        try {
            mAuth = FirebaseAuth.getInstance();
            mCurrentUser = mAuth.getCurrentUser();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

            switchIsAdmin.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b)
                    isAdmin = 1;
                else
                    isAdmin = 0;
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private boolean isValid() {

        if (TextUtils.isEmpty(txtFirstName.getText().toString())) {
            tilFirstName.setError("First Name Required !!!");
        } else {
            tilFirstName.setError(null);
            strFirstName = txtFirstName.getText().toString().trim();
        }

        if (TextUtils.isEmpty(txtLastName.getText().toString())) {
            tilLastName.setError("Last Name Required !!!");
        } else {
            tilLastName.setError(null);
            strLastName = txtLastName.getText().toString().trim();
        }

        if (TextUtils.isEmpty(txtMobile.getText().toString())) {
            tilMobile.setError("Mobile Required !!!");
        } else {
            tilMobile.setError(null);
            strMobile = txtMobile.getText().toString().trim();
        }

        if (tilFirstName.isErrorEnabled() || tilLastName.isErrorEnabled() ||tilMobile.isErrorEnabled()) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.btnSaveData)
    public void btnSaveDataTap(View view){
        if (isValid()) {
            saveUser = new Users(
                    mCurrentUser.getUid(),
                    strFirstName,
                    strLastName,
                    mCurrentUser.getEmail(),
                    strMobile,
                    isAdmin,
                    SharedPreferencesManager.getFCMToken()
            );
            saveData();
        }
    }

    private void saveData() {
        showApiDialog(this);
        if (isNetworkAvailable(this)) {

            mDatabaseReference.child(saveUser.getUserID()).setValue(saveUser)
                .addOnCompleteListener(task -> {
                    hideApiDialog();
                    if (task.isSuccessful()) {
                        SharedPreferencesManager.setUserDetails(saveUser);
                        NewIntentClearTop(ProfileDataScreen.this, MainActivity.class, true);
                    } else {
                        showToast(ProfileDataScreen.this, "Authentication failed.");
                    }
                });

        } else {
            showMessageInternet(this);
        }
    }
}