package com.example.shop_java.product.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.databinding.ProductItemBinding;
import com.example.shop_java.global_models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {

        ProductItemBinding productItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);


        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.productItemBinding.setViewModel(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setList(List<Product> productList) {

        this.productList = productList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding productItemBinding;

        public ProductViewHolder(@NonNull ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }
    }
}
