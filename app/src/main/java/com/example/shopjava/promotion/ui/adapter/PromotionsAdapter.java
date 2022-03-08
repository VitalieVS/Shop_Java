package com.example.shopjava.promotion.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.databinding.PromotionItemBinding;
import com.example.shopjava.promotion.PromotionItemActivity;
import com.example.shopjava.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.PromotionViewHolder>
        implements SelectedPromotion {

    private List<Promotion> promotionList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public PromotionsAdapter.PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                    int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PromotionItemBinding promotionItemBinding =
                PromotionItemBinding.inflate(layoutInflater, parent, false);

        return new PromotionViewHolder(promotionItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionsAdapter.PromotionViewHolder holder,
                                 int position) {

        final Promotion promotion = promotionList.get(position);
        holder.promotionItemBinding.setViewModel(promotion);
        holder.itemView.setOnClickListener(v -> selectedPromotion(promotion));
    }

    @Override
    public int getItemCount() {

        return promotionList.size();
    }

    public void setList(List<Promotion> promotionList) {

        this.promotionList = promotionList;
        notifyDataSetChanged();
    }

    @Override
    public void selectedPromotion(Promotion promotionModel) {

        context.startActivity(new Intent(context, PromotionItemActivity.class)
                .putExtra("SelectedPromotion", promotionModel));
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder {

        PromotionItemBinding promotionItemBinding;

        public PromotionViewHolder(@NonNull PromotionItemBinding promotionItemBinding) {
            super(promotionItemBinding.getRoot());
            this.promotionItemBinding = promotionItemBinding;
        }
    }
}
