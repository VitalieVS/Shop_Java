package com.example.shop_java.product.ingredients.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.databinding.IngredientItemBinding;
import com.example.shop_java.models.Ingredient;

import java.util.List;

public class IngredientAdapter extends
        RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;

    public IngredientAdapter(List<Ingredient> ingredients) {

        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        IngredientItemBinding ingredientItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.ingredient_item, parent, false);

        return new IngredientViewHolder(ingredientItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientViewHolder holder, final int position) {

        final Ingredient ingredient = ingredientList.get(position);

        holder.ingredientItemBinding.setIngredient(ingredient);

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void setList(List<Ingredient> ingredients) {

        this.ingredientList = ingredients;
        notifyDataSetChanged();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        IngredientItemBinding ingredientItemBinding;

        public IngredientViewHolder(@NonNull IngredientItemBinding ingredientItemBinding) {

            super(ingredientItemBinding.getRoot());
            this.ingredientItemBinding = ingredientItemBinding;
        }
    }
}

