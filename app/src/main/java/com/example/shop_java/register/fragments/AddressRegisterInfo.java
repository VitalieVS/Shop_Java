package com.example.shop_java.register.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shop_java.R;
import com.example.shop_java.databinding.SecondRegisterAddressBinding;
import com.example.shop_java.register.service.RegisterService;

public class AddressRegisterInfo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        requireActivity().getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        RegisterService registerService = RegisterService.getInstance();

        SecondRegisterAddressBinding secondRegisterAddressBinding =
                DataBindingUtil.inflate(inflater, R.layout.second_register_address,
                        container, false);

        secondRegisterAddressBinding.setRegisterService(registerService);
        secondRegisterAddressBinding.setAddress(registerService.getAddress());

        return secondRegisterAddressBinding.getRoot();
    }
}
