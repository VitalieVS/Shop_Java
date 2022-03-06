package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.cart.container.EmptyCartFragment;
import com.example.shop_java.cart.implementation.adapter.ProductPromotionList;
import com.example.shop_java.cart.service.CartService;
import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.databinding.BottomSheetOrderBinding;
import com.example.shop_java.databinding.FragmentCartBinding;
import com.example.shop_java.login.service.UserService;
import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {

    private BottomSheetDialog bottomSheetDialog;

    private BottomSheetOrderBinding bindingSheet;

    private UserService userService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentCartBinding fragmentCartBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cart, container, false);

        CartService cartService = CartService.getInstance();

        userService = UserService.getInstance();

        cartService.setContext(Objects.requireNonNull(container).getContext());

        fragmentCartBinding.setCartService(cartService);

        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        BottomNavigationView bottomNavigationView =
                requireActivity().findViewById(R.id.bottom_navigation);

        bottomSheetDialog =
                new BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme);

        userService.setContext(requireContext());

        CartService cartService = CartService.getInstance();
        cartService.setFragmentActivity(requireActivity());

        RecyclerView recyclerView = requireView().findViewById(R.id.cartItemsRecyclerView);
        ProductPromotionList adapter = new ProductPromotionList();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageView imageView = requireView().findViewById(R.id.exitCartButton);
        imageView.setOnClickListener(v -> {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment()).commit();

            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.setSelectedItemId(R.id.home);
        });

        showFragmentEmptyCartFragment(cartService.getProductList(), cartService.getPromotionList());

        CartViewModel.productMutableLiveData.observe(getViewLifecycleOwner(), adapter::setProductDataSet);

        CartViewModel.promotionMutableLiveData.observe(getViewLifecycleOwner(), adapter::setPromotionDataSet);

        CartViewModel.stateMutableLiveData.observe(getViewLifecycleOwner(), state -> {

            if (state.equals(State.EMPTY_CART) && isAdded()) {

                showFragmentEmptyCartFragment(cartService.getProductList(), cartService.getPromotionList());
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else if (isAdded()) {

                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        CardView cardView = requireView().findViewById(R.id.order_items);

        cardView.setOnClickListener(v -> {

            if (userService.isAuthorised()) {

                bindingSheet = DataBindingUtil.inflate(
                        LayoutInflater.from(requireActivity()),
                        R.layout.bottom_sheet_order,
                        null,
                        false);

                bindingSheet.setCartService(cartService);

                bindingSheet.backButton.setOnClickListener(backButtonListener ->
                        bottomSheetDialog.cancel());

                bottomSheetDialog.setContentView(bindingSheet.mainContainer);
                bottomSheetDialog.show();
            } else {

                Toast.makeText(view.getContext(), "Please authorise first!",
                        Toast.LENGTH_SHORT).show();
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