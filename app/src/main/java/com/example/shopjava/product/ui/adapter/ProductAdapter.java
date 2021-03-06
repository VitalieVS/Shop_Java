package com.example.shopjava.product.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.R;
import com.example.shopjava.cart.service.CartService;
import com.example.shopjava.databinding.BottomSheetItemBinding;
import com.example.shopjava.databinding.ProductItemBinding;
import com.example.shopjava.models.Product;
import com.example.shopjava.product.ingredients.ui.IngredientAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private final List<Product> productList;
    private BottomSheetItemBinding bindingSheet;
    private BottomSheetDialog bottomSheetDialog;
    private IngredientAdapter ingredientAdapter;
    private CartService cartService;

    public ProductAdapter(List<Product> productList) {

        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {

        context = parent.getContext();

        cartService = CartService.getInstance();
        cartService.setContext(parent.getContext());

        com.example.shopjava.databinding.ProductItemBinding productItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);

        bottomSheetDialog =
                new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);

        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

        final Product product = productList.get(position);

        holder.productItemBinding.setProduct(product);

        holder.productItemBinding.setCartService(cartService);

        holder.itemView.setOnClickListener(v -> {


            bindingSheet = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.bottom_sheet_item,
                    null,
                    false);

            bindingSheet.setProduct(product);
            bindingSheet.setCartService(cartService);

            bottomSheetDialog.setContentView(bindingSheet.bottomSheetProductContainer);

            final RecyclerView recyclerView1 = bindingSheet.ingredientRecyclerView;
            ingredientAdapter = new IngredientAdapter(product.getIngredients());
            recyclerView1.setAdapter(ingredientAdapter);

            bottomSheetDialog.show();

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding productItemBinding;

        public ProductViewHolder(@NonNull ProductItemBinding productItemBinding) {

            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }
    }
}
