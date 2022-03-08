package com.example.shopjava.orders.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.R;
import com.example.shopjava.databinding.OrderItemBinding;
import com.example.shopjava.login.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList = new ArrayList<>();

    public OrderAdapter(List<Order> orderList) {

        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        OrderItemBinding orderItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.order_item, parent, false);

        return new OrderAdapter.OrderViewHolder(orderItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        final Order order = orderList.get(position);

        holder.orderItemBinding.setOrder(order);
    }

    @Override
    public int getItemCount() {

        return orderList.size();
    }

    public void setOrderList(List<Order> orderList) {

        this.orderList = orderList;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        OrderItemBinding orderItemBinding;

        public OrderViewHolder(@NonNull OrderItemBinding orderItemBinding) {

            super(orderItemBinding.getRoot());
            this.orderItemBinding = orderItemBinding;
        }
    }

}
