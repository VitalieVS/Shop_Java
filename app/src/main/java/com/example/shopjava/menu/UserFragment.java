package com.example.shopjava.menu;

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

import com.example.shopjava.R;
import com.example.shopjava.databinding.FragmentUserMenuBinding;
import com.example.shopjava.login.service.UserService;
import com.example.shopjava.login.viewmodel.LoginViewModel;
import com.example.shopjava.orders.adapter.OrderAdapter;

import java.util.Objects;

public class UserFragment extends Fragment {

    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        UserService userService = UserService.getInstance();

        FragmentUserMenuBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_menu, container, false);

        View view = binding.getRoot();

        userService.setContext(Objects.requireNonNull(container).getContext());
        userService.setFragmentActivity(requireActivity());

        binding.setLoginViewModel(loginViewModel);

        binding.setUserService(userService);

        loginViewModel.getUser(userService.getToken(), userService.getLogin());

        RecyclerView recyclerView = view.findViewById(R.id.userOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LoginViewModel.USER_MUTABLE_LIVE_DATA.observe(getViewLifecycleOwner(), user -> {

            if (user == null && isAdded()) {

                userService.logout();
            }

            if (user != null && isAdded()) {

                OrderAdapter orderAdapter = new OrderAdapter(user.getOrders());
                userService.setAddress(user.getAddress());
                userService.setCashBack(user.getTotalCashBack());
                recyclerView.setAdapter(orderAdapter);
                binding.setUser(user);
            }

        });


        return view;
    }
}
