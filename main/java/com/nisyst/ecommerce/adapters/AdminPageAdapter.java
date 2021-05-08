package com.nisyst.ecommerce.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nisyst.ecommerce.model.Inventory;
import com.nisyst.ecommerce.ui.main.admin.fragments.InventoryFragment;
import com.nisyst.ecommerce.ui.main.admin.fragments.ItemFragment;
import com.nisyst.ecommerce.ui.main.admin.fragments.OrderFragment;

public class AdminPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    int mTotalTabs;

    public AdminPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public AdminPageAdapter(Context context , FragmentManager fragmentManager , int totalTabs) {
        super(fragmentManager);
        mContext = context;
        mTotalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                InventoryFragment inventoryFragment = new InventoryFragment();
                return inventoryFragment;
            case 1:
                ItemFragment itemFragment = new ItemFragment();
                return itemFragment;
            case 2:
                OrderFragment orderFragment = new OrderFragment();
                return orderFragment;
            default:
                return null;
        }
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Inventory";
        else if (position == 1)
            title = "Items";
        else if (position == 2)
            title = "Orders";
        return title;
    }*/

    @Override
    public int getCount() {
        return mTotalTabs;
    }
}
