package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shop_java.R;
import com.example.shop_java.databinding.FragmentLoginBinding;
import com.example.shop_java.login.model.LoginRequest;
import com.example.shop_java.login.service.UserService;
import com.example.shop_java.login.viewmodel.AuthorisationStatus;
import com.example.shop_java.login.viewmodel.LoginViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        UserService userService = UserService.getInstance();
        userService.setContext(requireActivity());

        LoginViewModel.LOGIN_STATUS.observe(requireActivity(), status -> {

            if (status.equals(AuthorisationStatus.SUCCESS) && isAdded()) {

                userService.setAuthorised(true, LoginViewModel.TOKEN.getValue(),
                        LoginViewModel.LOGIN.getValue());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new UserFragment()).commit();
            } else if (isAdded() && status.equals(AuthorisationStatus.FAILED)) {
                Toast.makeText(requireActivity(), "Wrong credentials!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        FragmentLoginBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);

        View view = binding.getRoot();
        UserService userService = UserService.getInstance();
        userService.setContext(Objects.requireNonNull(container).getContext());

        binding.setLoginRequest(new LoginRequest());
        binding.setLoginViewModel(loginViewModel);
        binding.setUserService(userService);

        return view;
    }
}