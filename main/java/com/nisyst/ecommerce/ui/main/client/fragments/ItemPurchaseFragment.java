package com.nisyst.ecommerce.ui.main.client.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.adapters.InventoryAdapter;
import com.nisyst.ecommerce.model.Inventory;
import com.nisyst.ecommerce.model.Items;
import com.nisyst.ecommerce.ui.main.admin.fragments.InventoryFragment;
import com.nisyst.ecommerce.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nisyst.ecommerce.util.Utility.showToast;

public class ItemPurchaseFragment extends Fragment {

    @BindView(R.id.rvItemPurchase)
    RecyclerView rvItemPurchase;
    @BindView(R.id.lblNoDataItemPurchase)
    TextView lblNoDataItemPurchase;

    private ArrayList<Items> mItemList;
    DatabaseReference dbInventory;

    public static ItemPurchaseFragment newInstance() {
        return new ItemPurchaseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_purchase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        showNoData();
        mItemList = new ArrayList<>();
        Utility.showApiDialog(getActivity());
        dbInventory = FirebaseDatabase.getInstance().getReference("items");

        dbInventory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mItemList.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Items items = postSnapshot.getValue(Items.class);
                    mItemList.add(items);
                }

                Utility.hideApiDialog();
                if (mItemList.size() == 0) {
                    showNoData();
                } else {
                    hideNoData();
                    //InventoryAdapter adapter = new InventoryAdapter(getActivity(), mInventoryList);
                    //rvItemPurchase.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast(getActivity(), error.getMessage());
            }
        });
    }

    private void showNoData() {
        lblNoDataItemPurchase.setVisibility(View.VISIBLE);
        rvItemPurchase.setVisibility(View.GONE);
    }

    private void hideNoData() {
        lblNoDataItemPurchase.setVisibility(View.GONE);
        rvItemPurchase.setVisibility(View.VISIBLE);
    }

}