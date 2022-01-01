package com.example.shop_java.cart.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.CartViewModel;
import com.example.shop_java.databinding.CartItemBinding;
import com.example.shop_java.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private CartViewModel cartViewModel;
    private List<Product> productList = new ArrayList<>();

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        cartViewModel =
                ViewModelProviders.of((FragmentActivity) parent.getContext()).get(CartViewModel.class);

        CartItemBinding cartItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false);

        return new CartViewHolder(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder,
                                 int position) {

        final Product product = productList.get(position);

        holder.cartItemBinding.setProduct(product);

        holder.cartItemBinding.setCartViewModel(cartViewModel);

    }

    public void setList(List<Product> productList) {

        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return productList.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder {

        CartItemBinding cartItemBinding;

        public CartViewHolder(@NonNull CartItemBinding cartItemBinding) {

            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;
        }
    }

}
