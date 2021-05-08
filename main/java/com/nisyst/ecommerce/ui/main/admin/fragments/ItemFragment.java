package com.nisyst.ecommerce.ui.main.admin.fragments;

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
import com.nisyst.ecommerce.adapters.ItemAdapter;
import com.nisyst.ecommerce.model.Inventory;
import com.nisyst.ecommerce.model.Items;
import com.nisyst.ecommerce.ui.auth.ProfileDataScreen;
import com.nisyst.ecommerce.ui.main.MainActivity;
import com.nisyst.ecommerce.util.SharedPreferencesManager;
import com.nisyst.ecommerce.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nisyst.ecommerce.util.Utility.NewIntentClearTop;
import static com.nisyst.ecommerce.util.Utility.hideApiDialog;
import static com.nisyst.ecommerce.util.Utility.isNetworkAvailable;
import static com.nisyst.ecommerce.util.Utility.showApiDialog;
import static com.nisyst.ecommerce.util.Utility.showMessageInternet;
import static com.nisyst.ecommerce.util.Utility.showToast;


public class ItemFragment extends Fragment {

    @BindView(R.id.rvItems)
    RecyclerView rvItems;
    @BindView(R.id.lblNoDataItems)
    TextView lblNoDataItems;

    private ArrayList<Items> mItemsList;
    DatabaseReference dbInventory;

    int i = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        showNoData();
        mItemsList = new ArrayList<>();
        Utility.showApiDialog(getActivity());
        dbInventory = FirebaseDatabase.getInstance().getReference("item");

        dbInventory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mItemsList.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Items items = postSnapshot.getValue(Items.class);
                    mItemsList.add(items);
                }

                Utility.hideApiDialog();
                if (mItemsList.size() == 0) {
                    showNoData();
                } else {
                    hideNoData();
                    //InventoryAdapter adapter = new InventoryAdapter(getActivity(), mInventoryList);
                    ItemAdapter adapter = new ItemAdapter(getActivity(), mItemsList);
                    rvItems.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast(getActivity(), error.getMessage());
            }
        });
    }

    @OnClick(R.id.btnAddProduct)
    public void btnAddProductTap(View view) {
        inputData();
    }

    private void inputData() {
        Items items1 = new Items();
        items1.setProductCode("ITEM_"+i);
        items1.setProductName("Apple");
        items1.setProductQty(10);
        items1.setProductPrice(50);

        showApiDialog(getActivity());
        if (isNetworkAvailable(getActivity())) {
            dbInventory.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean flag = false;
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Items items = postSnapshot.getValue(Items.class);
                        if (items.getProductCode().equals(items1.getProductCode())) {
                            items1.setProductQty(items.getProductQty() + items1.getProductQty());
                            items1.setTotalQty(items.getTotalQty() + items1.getProductQty());
                            items1.setRemainingQty(items.getRemainingQty() + items1.getProductQty());
                            break;
                        }
                    }
                    saveData(items1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            showMessageInternet(getActivity());
        }

    }

    private void saveData(Items items) {

        /*Items items = new Items();
        items.setProductCode("ITEM_"+i);
        items.setProductName("Apple");
        items.setProductQty(10);
        items.setProductPrice(50);
        items.setRemainingQty(10);
        items.setTotalQty(10);
        items.setSoldQty(0);

        i++;*/


        showApiDialog(getActivity());
        if (isNetworkAvailable(getActivity())) {

            dbInventory.child(items.getProductCode()).setValue(items)
                    .addOnCompleteListener(task -> {
                        hideApiDialog();
                        if (task.isSuccessful()) {

                        } else {
                            showToast(getActivity(), "Insert failed.");
                        }
                    });

        } else {
            showMessageInternet(getActivity());
        }
    }

    private void showNoData() {
        lblNoDataItems.setVisibility(View.VISIBLE);
        rvItems.setVisibility(View.GONE);
    }

    private void hideNoData() {
        lblNoDataItems.setVisibility(View.GONE);
        rvItems.setVisibility(View.VISIBLE);
    }
}