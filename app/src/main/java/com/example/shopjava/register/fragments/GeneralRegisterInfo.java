package com.example.shopjava.register.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shopjava.R;
import com.example.shopjava.databinding.FirstRegisterGeneralBinding;
import com.example.shopjava.login.model.Address;
import com.example.shopjava.register.model.RegisterRequest;
import com.example.shopjava.register.service.RegisterService;

public class GeneralRegisterInfo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        RegisterService registerService = RegisterService.getInstance();

        FirstRegisterGeneralBinding firstRegisterGeneralBinding =
                DataBindingUtil.inflate(inflater, R.layout.first_register_general,
                        container, false);

        registerService.setRegisterRequest(new RegisterRequest());
        registerService.setAddress(new Address());

        firstRegisterGeneralBinding.setRegisterService(registerService);
        firstRegisterGeneralBinding.setRegisterRequest(registerService.getRegisterRequest());

        return firstRegisterGeneralBinding.getRoot();
    }

}
