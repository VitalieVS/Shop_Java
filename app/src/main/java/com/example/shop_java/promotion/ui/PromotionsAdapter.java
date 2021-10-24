package com.example.shop_java.promotion.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.promotion_activity.PromotionItemActivity;
import com.example.shop_java.databinding.PromotionItemBinding;
import com.example.shop_java.promotion.model.PromotionModel;
import com.example.shop_java.promotion.ui.adapter_interface.SelectedPromotion;
import java.util.ArrayList;
import java.util.List;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.PromotionViewHolder>
        implements SelectedPromotion {

    private List<PromotionModel> promotionList = new ArrayList<>();
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

        final PromotionModel promotion = promotionList.get(position);
        holder.promotionItemBinding.setViewModel(promotion);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPromotion(promotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public void setList(List<PromotionModel> promotionList) {

        this.promotionList = promotionList;
        notifyDataSetChanged();
    }

    @Override
    public void selectedPromotion(PromotionModel promotionModel) {

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
