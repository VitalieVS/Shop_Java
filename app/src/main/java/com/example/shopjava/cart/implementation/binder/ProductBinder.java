package com.example.shopjava.cart.implementation.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopjava.R;
import com.example.shopjava.cart.binder.DataBindAdapter;
import com.example.shopjava.cart.binder.DataBinder;
import com.example.shopjava.cart.implementation.binder.remover.RemoveProduct;
import com.example.shopjava.cart.service.CartService;
import com.example.shopjava.databinding.CartItemBinding;
import com.example.shopjava.models.Product;

import java.util.ArrayList;
import java.util.List;


@BindingMethods({
        @BindingMethod(type = android.widget.ImageView.class,
                attribute = "app:srcCompat",
                method = "setImageDrawable")})
public class ProductBinder extends DataBinder<ProductBinder.ViewHolder> implements RemoveProduct {

    private List<Product> productList = new ArrayList<>();

    private CartService cartService;

    private int position;

    public ProductBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {

        cartService = CartService.getInstance();

        cartService.setContext(parent.getContext());

        CartItemBinding cartItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false);

        return new ViewHolder(cartItemBinding);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {

        this.position = position;

        Product product = productList.get(position);

        holder.cartItemBinding.setProduct(product);

        holder.cartItemBinding.setRemoveInterface(this);

        holder.cartItemBinding.setCartService(cartService);

    }

    public void addAll(List<Product> dataSet) {

        this.productList = dataSet;
        notifyBinderDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return this.productList.size();
    }

    @Override
    public void removeProductFromCart(Product product) {

        cartService.removeFromCart(product);
        notifyBinderItemRemoved(this.position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CartItemBinding cartItemBinding;

        public ViewHolder(@NonNull CartItemBinding cartItemBinding) {

            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;
        }
    }
}
