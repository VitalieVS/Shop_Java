package com.example.shop_java.cart.implementation.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.binder.DataBindAdapter;
import com.example.shop_java.cart.binder.DataBinder;
import com.example.shop_java.cart.service.CartService;
import com.example.shop_java.databinding.CartPromotionItemBinding;
import com.example.shop_java.promotion.model.Promotion;
import com.example.shop_java.promotion.remover.RemovePromotion;

import java.util.ArrayList;
import java.util.List;

public class PromotionBinder extends DataBinder<PromotionBinder.ViewHolder> implements RemovePromotion {

    private List<Promotion> promotionList = new ArrayList<>();
    private int position;
    private CartService cartService;

    public PromotionBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public PromotionBinder.ViewHolder newViewHolder(ViewGroup parent) {

        cartService = CartService.getInstance();
        cartService.setContext(parent.getContext());

        CartPromotionItemBinding cartItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_promotion_item, parent, false);


        return new PromotionBinder.ViewHolder(cartItemBinding);
    }

    @Override
    public void bindViewHolder(PromotionBinder.ViewHolder holder, int position) {

        this.position = position;

        Promotion promotion = promotionList.get(position);

        holder.promotionItemBinding.setPromotion(promotion);
        holder.promotionItemBinding.setRemoveInterface(this);
    }

    public void addAll(List<Promotion> dataSet) {

        this.promotionList = dataSet;
        notifyBinderDataSetChanged();
    }


    @Override
    public int getItemCount() {

        return promotionList.size();
    }

    @Override
    public void removePromotionFromCart(Promotion promotion) {

        cartService.removePromotionFromCart(promotion);
        notifyBinderItemRemoved(this.position);

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        CartPromotionItemBinding promotionItemBinding;

        public ViewHolder(@NonNull CartPromotionItemBinding promotionItemBinding) {

            super(promotionItemBinding.getRoot());
            this.promotionItemBinding = promotionItemBinding;
        }
    }
}

