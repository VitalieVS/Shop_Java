package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.container.EmptyCartFragment;
import com.example.shop_java.cart.implementation.adapter.SampleListAdapter;
import com.example.shop_java.cart.service.CartService;
import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.databinding.FragmentCartBinding;
import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {
    CartViewModel cartViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentCartBinding fragmentCartBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cart, container, false
        );

        CartService cartService = CartService.getInstance();

        cartService.setContext(Objects.requireNonNull(container).getContext());

        fragmentCartBinding.setCartService(cartService);

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        return (View) fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        BottomNavigationView bottomNavigationView =
                requireActivity().findViewById(R.id.bottom_navigation);

        CartService cartService = CartService.getInstance();

        RecyclerView recyclerView = requireView().findViewById(R.id.cartItemsRecyclerView);
        SampleListAdapter adapter = new SampleListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showFragmentEmptyCartFragment(cartService.getProductList(), cartService.getPromotionList());

        CartViewModel.productMutableLiveData.observe(requireActivity(), adapter::setProductDataSet);

        CartViewModel.promotionMutableLiveData.observe(requireActivity(), adapter::setPromotionDataSet);

        CartViewModel.stateMutableLiveData.observe(requireActivity(), state -> {

            if (state.equals(State.EMPTY_CART) && isAdded()) {

                showFragmentEmptyCartFragment(cartService.getProductList(), cartService.getPromotionList());
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else if (isAdded()) {

                bottomNavigationView.setVisibility(View.GONE);
            }
        });


    }

    private void showFragmentEmptyCartFragment(List<Product> productList, List<Promotion> promotionList) {

        if (productList.size() == 0 && promotionList.size() == 0) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new EmptyCartFragment()).commit();
        }
    }

}