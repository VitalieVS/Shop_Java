package com.example.shop_java.cart.sample.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.CartViewModel;
import com.example.shop_java.cart.binder.DataBindAdapter;
import com.example.shop_java.cart.binder.DataBinder;
import com.example.shop_java.databinding.CartItemBinding;
import com.example.shop_java.models.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductBinder extends DataBinder<ProductBinder.ViewHolder> {

    private final List<Product> productList = new ArrayList<>();
    private CartViewModel cartViewModel;

    public ProductBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {

        cartViewModel =
                new ViewModelProvider((FragmentActivity) parent.getContext()).get(CartViewModel.class);

        CartItemBinding cartItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false);

        return new ViewHolder(cartItemBinding);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.cartItemBinding.setProduct(product);

        holder.cartItemBinding.setCartViewModel(cartViewModel);
    }

    public void addAll(List<Product> dataSet) {

        productList.addAll(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return this.productList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        CartItemBinding cartItemBinding;

        public ViewHolder(@NonNull CartItemBinding cartItemBinding) {

            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;
        }
    }
}
