package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.CartViewModel;
import com.example.shop_java.cart.ui.adapter.CartAdapter;
import com.example.shop_java.connection.NoInternetFragment;


public class CartFragment extends Fragment {

    CartViewModel cartViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        RecyclerView recyclerView = requireView().findViewById(R.id.cartItemsRecyclerView);
        final CartAdapter adapter = new CartAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        CartViewModel.productMutableLiveData.observe(requireActivity(), productModels -> {
            adapter.setList(productModels);
            if (productModels.isEmpty() && isAdded()) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NoInternetFragment()).commit();

            }
        });
    }

}