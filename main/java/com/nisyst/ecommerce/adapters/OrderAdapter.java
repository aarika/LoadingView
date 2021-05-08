package com.nisyst.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.model.Inventory;
import com.nisyst.ecommerce.model.Orders;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private ArrayList<Orders> mOrderList;

    public OrderAdapter() {
    }

    public OrderAdapter(Context mContext, ArrayList<Orders> mOrderList) {
        this.mContext = mContext;
        this.mOrderList = mOrderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bindData(mOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderList == null ? 0 : mOrderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemOrderProductCode)
        TextView itemOrderProductCode;
        @BindView(R.id.itemOrderUserId)
        TextView itemOrderUserId;
        @BindView(R.id.itemOrderTotalQty)
        TextView itemOrderTotalQty;
        @BindView(R.id.itemOrderTotalPrice)
        TextView itemOrderTotalPrice;
        @BindView(R.id.itemOrderAccept)
        AppCompatButton itemOrderAccept;
        @BindView(R.id.itemOrderReject)
        AppCompatButton itemOrderReject;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Orders inventory) {
            /*itemInventoryProductCode.setText(inventory.getProductCode());
            itemInventoryName.setText(inventory.getProductName());
            itemInventoryTotalQty.setText(String.valueOf(inventory.getTotalQty()));
            itemInventorySoldQty.setText(String.valueOf(inventory.getSoldQty()));
            itemInventoryLeftQty.setText(String.valueOf(inventory.getRemainingQty()));*/
        }
    }
}
