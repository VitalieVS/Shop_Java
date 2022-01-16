package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.CartViewModel;
import com.example.shop_java.cart.container.EmptyCartFragment;
import com.example.shop_java.cart.sample.adapter.SampleListAdapter;
import com.example.shop_java.models.Product;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    CartViewModel cartViewModel;
    LayoutInflater inflater;
    ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        List<Product> productList = new ArrayList<>();
        List<Promotion> promotionList = new ArrayList<>();

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        RecyclerView recyclerView = requireView().findViewById(R.id.cartItemsRecyclerView);
        SampleListAdapter adapter = new SampleListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CartViewModel.productMutableLiveData.observe(requireActivity(), productModels -> {

            productList.addAll(productModels);
            adapter.setProductDataSet(productModels);
            if (isAdded()) {
                showFragment(productList, promotionList);
            }
        });


        CartViewModel.promotionMutableLiveData.observe(requireActivity(), promotionModels -> {

            promotionList.addAll(promotionModels);
            adapter.setPromotionDataSet(promotionModels);
            if (isAdded()) {
                showFragment(productList, promotionList);
            }
        });

        showFragment(productList, promotionList);
    }

    private void showFragment(List<Product> productList, List<Promotion> promotionList) {

        if (productList.size() == 0 && promotionList.size() == 0) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new EmptyCartFragment()).commit();
        }
    }

}