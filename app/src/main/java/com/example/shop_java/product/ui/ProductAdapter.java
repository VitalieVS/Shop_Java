package com.example.shop_java.product.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.databinding.BottomSheetItemBinding;
import com.example.shop_java.databinding.ProductItemBinding;
import com.example.shop_java.global_models.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private BottomSheetItemBinding bindingSheet;
    private BottomSheetDialog bottomSheetDialog;

    public ProductAdapter(List<Product> productList) {

        this.productList = productList;

        for (int i = 0; i < productList.size(); i++) {
            Log.d("DEBUG", "price: " + productList.get(i).getPrice());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {

        context = parent.getContext();
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);

        bottomSheetDialog =
                new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);

        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

        final Product product = productList.get(position);

        holder.productItemBinding.setViewModel(product);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindingSheet = DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.bottom_sheet_item,
                        null,
                        false);
                // possible temporary fix
                product.setPriceCopy(product.getPrice());

                bindingSheet.setProduct(product);



                bottomSheetDialog.setContentView(bindingSheet.bottomSheetProductContainer);

                bottomSheetDialog.show();

                bindingSheet.bottomSheetProductContainer.findViewById(R.id.closeView)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetDialog.dismiss();
                            }
                        });
            }
        });
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
