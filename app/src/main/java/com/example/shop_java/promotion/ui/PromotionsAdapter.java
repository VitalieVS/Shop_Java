package com.example.shop_java.promotion.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.promotion.model.PromotionModel;

import java.util.ArrayList;
import java.util.List;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.PromotionViewHolder> {
    private List<PromotionModel> promotionList = new ArrayList<>();

    @NonNull
    @Override
    public PromotionsAdapter.PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PromotionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionsAdapter.PromotionViewHolder holder, int position) {
        holder.promo_id.setText("try");
        holder.promo_title.setText("again");
        holder.promo_body.setText("another one");
    }

    @Override
    public int getItemCount() {
        System.out.println("SIZE:");
        System.out.println(promotionList.size());
        return promotionList.size();
    }

    public void setList(List<PromotionModel> promotionList) {
        this.promotionList = promotionList;
        notifyDataSetChanged();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {
        TextView promo_id, promo_title, promo_body;

        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);
            promo_id = itemView.findViewById(R.id.promo_id);
            promo_title = itemView.findViewById(R.id.promo_title);
            promo_body = itemView.findViewById(R.id.promo_body);
        }
    }
}
