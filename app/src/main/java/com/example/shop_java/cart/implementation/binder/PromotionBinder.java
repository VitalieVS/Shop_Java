package com.example.shop_java.cart.implementation.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.binder.DataBindAdapter;
import com.example.shop_java.cart.binder.DataBinder;
import com.example.shop_java.databinding.PromotionItemBinding;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public class PromotionBinder extends DataBinder<PromotionBinder.ViewHolder> {

    private final List<Promotion> promotionList = new ArrayList<>();

    public PromotionBinder(DataBindAdapter dataBindAdapter) {

        super(dataBindAdapter);
    }

    @Override
    public PromotionBinder.ViewHolder newViewHolder(ViewGroup parent) {

        PromotionItemBinding cartItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.promotion_item, parent, false);

        return new PromotionBinder.ViewHolder(cartItemBinding);
    }

    @Override
    public void bindViewHolder(PromotionBinder.ViewHolder holder, int position) {

        final Promotion promotion = promotionList.get(position);

        holder.promotionItemBinding.setViewModel(promotion);
    }

    public void addAll(List<Promotion> dataSet) {

        promotionList.addAll(dataSet);
        notifyBinderDataSetChanged();
    }


    @Override
    public int getItemCount() {

        return promotionList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        PromotionItemBinding promotionItemBinding;

        public ViewHolder(@NonNull PromotionItemBinding promotionItemBinding) {

            super(promotionItemBinding.getRoot());
            this.promotionItemBinding = promotionItemBinding;
        }
    }
}

