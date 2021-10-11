package com.example.shop_java.category.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.PromotionItemActivity;
import com.example.shop_java.category.model.CategoryModel;
import com.example.shop_java.category.ui.adapter_interface.SelectedCategory;
import com.example.shop_java.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>
        implements SelectedCategory {

    private List<CategoryModel> categoryList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public CategoriesAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategoryItemBinding categoryItemBinding =
                CategoryItemBinding.inflate(layoutInflater, parent, false);

        return new CategoriesAdapter.CategoryViewHolder(categoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.CategoryViewHolder holder,
                                 int position) {
        final CategoryModel category = categoryList.get(position);
        holder.categoryItemBinding.setViewModel(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCategory(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public void selectedCategory(CategoryModel categoryModel) {
        Intent intent = new Intent(context, PromotionItemActivity.class);
        intent.putExtra("categoryID", categoryModel.getCategoryId());
        context.startActivity(intent);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding categoryItemBinding;

        public CategoryViewHolder(@NonNull CategoryItemBinding categoryItemBinding) {
            super(categoryItemBinding.getRoot());
            this.categoryItemBinding = categoryItemBinding;
        }
    }
}
