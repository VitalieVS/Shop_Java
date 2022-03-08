package com.example.shopjava.product.ingredients.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.R;
import com.example.shopjava.databinding.IngredientItemBinding;
import com.example.shopjava.models.Ingredient;

import java.util.List;

public class IngredientAdapter extends
        RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;

    public IngredientAdapter(List<Ingredient> ingredients) {

        this.ingredientList = ingredients;
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
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        IngredientItemBinding ingredientItemBinding;

        public IngredientViewHolder(@NonNull IngredientItemBinding ingredientItemBinding) {

            super(ingredientItemBinding.getRoot());
            this.ingredientItemBinding = ingredientItemBinding;
        }
    }
}

