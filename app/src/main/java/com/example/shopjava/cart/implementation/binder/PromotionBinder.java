package com.example.shopjava.cart.implementation.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.R;
import com.example.shopjava.cart.binder.DataBindAdapter;
import com.example.shopjava.cart.binder.DataBinder;
import com.example.shopjava.cart.service.CartService;
import com.example.shopjava.databinding.CartPromotionItemBinding;
import com.example.shopjava.promotion.model.Promotion;
import com.example.shopjava.promotion.remover.RemovePromotion;

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

        holder.promotionItemBinding.setCartService(cartService);
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

        cartService.removeFromCart(promotion);
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

