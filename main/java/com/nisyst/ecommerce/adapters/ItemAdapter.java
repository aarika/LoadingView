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
import com.nisyst.ecommerce.model.Items;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<Items> mItemList;

    public ItemAdapter() {
    }

    public ItemAdapter(Context mContext, ArrayList<Items> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindData(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemItemProductCode)
        TextView itemItemProductCode;
        @BindView(R.id.itemItemProductName)
        TextView itemItemProductName;
        @BindView(R.id.itemItemQty)
        TextView itemItemQty;
        @BindView(R.id.itemItemPrice)
        TextView itemItemPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Items items) {
            itemItemProductCode.setText(items.getProductCode());
            itemItemProductName.setText(items.getProductName());
            itemItemQty.setText(String.valueOf(items.getProductQty()));
            itemItemPrice.setText(String.valueOf(items.getProductPrice()));
        }
    }
}
