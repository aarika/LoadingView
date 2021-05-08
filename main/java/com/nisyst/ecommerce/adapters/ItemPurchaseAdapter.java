package com.nisyst.ecommerce.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.model.Items;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemPurchaseAdapter {
    public class ItemPurchaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemItemPurchaseProductCode)
        TextView itemItemPurchaseProductCode;
        @BindView(R.id.itemItemPurchaseProductName)
        TextView itemItemPurchaseProductName;
        @BindView(R.id.itemItemPurchaseQty)
        TextView itemItemPurchaseQty;
        @BindView(R.id.itemItemPurchasePrice)
        TextView itemItemPurchasePrice;

        public ItemPurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Items items) {
            itemItemPurchaseProductCode.setText(items.getProductCode());
            itemItemPurchaseProductName.setText(items.getProductName());
            itemItemPurchaseQty.setText(String.valueOf(items.getProductQty()));
            itemItemPurchasePrice.setText(String.valueOf(items.getProductPrice()));
        }
    }
}
