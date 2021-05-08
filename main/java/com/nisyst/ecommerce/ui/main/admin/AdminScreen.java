package com.nisyst.ecommerce.ui.main.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.adapters.AdminPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminScreen extends AppCompatActivity {

    @BindView(R.id.tabAdmin)
    TabLayout tabAdmin;
    @BindView(R.id.vpAdmin)
    ViewPager vpAdmin;

    AdminPageAdapter adminPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        try {

            tabAdmin.addTab(tabAdmin.newTab().setText("Inventory"));
            tabAdmin.addTab(tabAdmin.newTab().setText("Items"));
            tabAdmin.addTab(tabAdmin.newTab().setText("Orders"));
            tabAdmin.setTabGravity(TabLayout.GRAVITY_FILL);

            adminPageAdapter = new AdminPageAdapter(this, getSupportFragmentManager(), tabAdmin.getTabCount());
            vpAdmin.setAdapter(adminPageAdapter);

            /*tabAdmin.setupWithViewPager(vpAdmin);*/
            vpAdmin.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabAdmin));
            tabAdmin.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    vpAdmin.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}