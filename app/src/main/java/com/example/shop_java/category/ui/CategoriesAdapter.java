package com.example.shop_java.category.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.ProductActivity;
import com.example.shop_java.category.model.Category;
import com.example.shop_java.category.ui.adapter_interface.SelectedCategory;
import com.example.shop_java.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>
        implements SelectedCategory {

    private List<Category> categoryList = new ArrayList<>();
    private List<Category> filteredCategoryList = new ArrayList<>();
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

        final Category category = filteredCategoryList.get(position);
        holder.categoryItemBinding.setViewModel(category);
        holder.itemView.setOnClickListener(v -> selectedCategory(category));
    }

    @Override
    public int getItemCount() {

        return filteredCategoryList.size();
    }

    public void setList(List<Category> categoryList) {

        this.categoryList = categoryList;
        this.filteredCategoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public void selectedCategory(Category categoryModel) {

        context.startActivity(new Intent(context, ProductActivity.class)
                .putExtra("CategoryData", categoryModel));
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String key = constraint.toString();

                if (key.isEmpty()) {
                    filteredCategoryList = categoryList;
                } else {
                    filteredCategoryList = removeDuplicates(filterByProduct(key),
                            filterCategories(key));
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredCategoryList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredCategoryList = (List<Category>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<Category> filterCategories(final String key) {

        return categoryList.stream().filter(
                item -> item.getName().toLowerCase()
                        .contains(key.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Category> filterByProduct(final String key) {

        return categoryList.stream().filter(item -> item.getProductList().stream().anyMatch(
                product -> product.getTitle().toLowerCase().contains(key.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Category> removeDuplicates(List<Category> first,
                                           List<Category> second) {
        ArrayList<Category> list = new ArrayList<>(first);
        list.addAll(second);

        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        CategoryItemBinding categoryItemBinding;

        public CategoryViewHolder(@NonNull CategoryItemBinding categoryItemBinding) {

            super(categoryItemBinding.getRoot());
            this.categoryItemBinding = categoryItemBinding;
        }
    }

}
