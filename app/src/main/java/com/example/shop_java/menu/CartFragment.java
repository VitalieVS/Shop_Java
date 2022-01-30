package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.container.EmptyCartFragment;
import com.example.shop_java.cart.implementation.adapter.SampleListAdapter;
import com.example.shop_java.cart.service.CartService;
import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class CartFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);

        CartService cartService = CartService.getInstance();

        RecyclerView recyclerView = requireView().findViewById(R.id.cartItemsRecyclerView);
        SampleListAdapter adapter = new SampleListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showFragment(cartService.getProductList(), cartService.getPromotionList());

        CartViewModel.stateMutableLiveData.observe(requireActivity(), state -> {
            if (state.equals(State.EMPTY_CART) && isAdded()) {
                showFragment(cartService.getProductList(), cartService.getPromotionList());
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });


        CartViewModel.productMutableLiveData.observe(requireActivity(), adapter::setProductDataSet);

        if (cartService.getProductList().size() > 0 || cartService.getPromotionList().size() > 0) {

            bottomNavigationView.setVisibility(View.GONE);
        }
        CartViewModel.promotionMutableLiveData.observe(requireActivity(), adapter::setPromotionDataSet);

    }

    private void showFragment(List<Product> productList, List<Promotion> promotionList) {

        if (productList.size() == 0 && promotionList.size() == 0) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new EmptyCartFragment()).commit();
        }
    }

}