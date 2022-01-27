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

import com.example.shop_java.R;
import com.example.shop_java.databinding.FragmentUserMenuBinding;
import com.example.shop_java.login.service.UserService;
import com.example.shop_java.login.viewmodel.LoginViewModel;

import java.util.Objects;

public class UserFragment extends Fragment {

    private LoginViewModel loginViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        FragmentUserMenuBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_menu, container, false);

        View view = binding.getRoot();

        UserService userService = UserService.getInstance();
        userService.setContext(Objects.requireNonNull(container).getContext());
        userService.setFragmentActivity(requireActivity());

        binding.setLoginViewModel(loginViewModel);

        binding.setUserService(userService);

        loginViewModel.getUser(userService.getToken(), userService.getLogin());

        LoginViewModel.USER_MUTABLE_LIVE_DATA.observe(requireActivity(), binding::setUser);


        return view;
    }
}
