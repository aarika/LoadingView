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
import com.nisyst.ecommerce.adapters.OrderAdapter;
import com.nisyst.ecommerce.model.Inventory;
import com.nisyst.ecommerce.model.Orders;
import com.nisyst.ecommerce.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nisyst.ecommerce.util.Utility.showToast;

public class OrderFragment extends Fragment {

    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.lblNoDataOrder)
    TextView lblNoDataOrder;

    private ArrayList<Orders> mOrderList;
    DatabaseReference dbInventory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        showNoData();
        mOrderList = new ArrayList<>();
        Utility.showApiDialog(getActivity());
        dbInventory = FirebaseDatabase.getInstance().getReference("order");

        dbInventory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mOrderList.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Orders orders = postSnapshot.getValue(Orders.class);
                    mOrderList.add(orders);
                }

                Utility.hideApiDialog();
                if (mOrderList.size() == 0) {
                    showNoData();
                } else {
                    hideNoData();
                    //InventoryAdapter adapter = new InventoryAdapter(getActivity(), mInventoryList);
                    OrderAdapter adapter = new OrderAdapter(getActivity(), mOrderList);
                    rvOrder.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast(getActivity(), error.getMessage());
            }
        });
    }

    private void showNoData() {
        lblNoDataOrder.setVisibility(View.VISIBLE);
        rvOrder.setVisibility(View.GONE);
    }

    private void hideNoData() {
        lblNoDataOrder.setVisibility(View.GONE);
        rvOrder.setVisibility(View.VISIBLE);
    }
}