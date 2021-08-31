package com.example.shop_java.promotion.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.databinding.PromotionItemBinding;
import com.example.shop_java.promotion.model.PromotionModel;

import java.util.ArrayList;
import java.util.List;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.PromotionViewHolder> {
    private List<PromotionModel> promotionList = new ArrayList<>();

    @NonNull
    @Override
    public PromotionsAdapter.PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        PromotionItemBinding promotionItemBinding = PromotionItemBinding.inflate(layoutInflater, parent, false);


        return new PromotionViewHolder(promotionItemBinding);

        //PromotionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionsAdapter.PromotionViewHolder holder, int position) {
        PromotionModel promotion = promotionList.get(position);
        holder.promotionItemBinding.setViewModel(promotion);
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public void setList(List<PromotionModel> promotionList) {
        this.promotionList = promotionList;
        notifyDataSetChanged();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {

        PromotionItemBinding promotionItemBinding;


        public PromotionViewHolder(@NonNull PromotionItemBinding promotionItemBinding) {
            super(promotionItemBinding.getRoot());
            this.promotionItemBinding = promotionItemBinding;
        }
    }
}
