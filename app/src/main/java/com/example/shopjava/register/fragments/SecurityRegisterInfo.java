package com.example.shopjava.register.fragments;

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

import com.example.shopjava.R;
import com.example.shopjava.connection.NoInternetFragment;
import com.example.shopjava.databinding.ThirdRegisterSecurityBinding;
import com.example.shopjava.register.service.RegisterService;
import com.example.shopjava.register.viewmodel.RegisterStatus;
import com.example.shopjava.register.viewmodel.RegisterViewModel;

public class SecurityRegisterInfo extends Fragment {

    private RegisterViewModel registerViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        RegisterService registerService = RegisterService.getInstance();

        ThirdRegisterSecurityBinding thirdRegisterSecurityBinding =
                DataBindingUtil.inflate(inflater, R.layout.third_register_security,
                        container, false);

        thirdRegisterSecurityBinding.setRegisterService(registerService);
        thirdRegisterSecurityBinding.setRegisterRequest(registerService.getRegisterRequest());

        return thirdRegisterSecurityBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        RegisterViewModel.REGISTER_RESPONSE.observe(getViewLifecycleOwner(), response -> {

            if (response.equals(RegisterStatus.NO_INTERNET) && isAdded()) {

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new NoInternetFragment()).commit();
            }

            if (response.equals(RegisterStatus.ERROR) && isAdded()) {

                Toast.makeText(view.getContext(),
                        "User already exists", Toast.LENGTH_SHORT).show();
            }

            if (response.equals(RegisterStatus.REGISTERED) && isAdded()) {

                registerViewModel.resetResponse();

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new SuccessRegister()).commit();

            }
        });
    }
}
