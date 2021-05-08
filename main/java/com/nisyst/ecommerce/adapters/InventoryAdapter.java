package com.nisyst.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.model.Inventory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private Context mContext;
    private ArrayList<Inventory> mInventoryList;

    public InventoryAdapter(Context mContext, ArrayList<Inventory> mInventoryList) {
        this.mContext = mContext;
        this.mInventoryList = mInventoryList;
    }

    public InventoryAdapter() {
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        holder.bindData(mInventoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return mInventoryList == null ? 0 : mInventoryList.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemInventoryProductCode)
        TextView itemInventoryProductCode;
        @BindView(R.id.itemInventoryName)
        TextView itemInventoryName;
        @BindView(R.id.itemInventoryTotalQty)
        TextView itemInventoryTotalQty;
        @BindView(R.id.itemInventorySoldQty)
        TextView itemInventorySoldQty;
        @BindView(R.id.itemInventoryLeftQty)
        TextView itemInventoryLeftQty;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Inventory inventory) {
            itemInventoryProductCode.setText(inventory.getProductCode());
            itemInventoryName.setText(inventory.getProductName());
            itemInventoryTotalQty.setText(String.valueOf(inventory.getTotalQty()));
            itemInventorySoldQty.setText(String.valueOf(inventory.getSoldQty()));
            itemInventoryLeftQty.setText(String.valueOf(inventory.getRemainingQty()));
        }
    }
}
