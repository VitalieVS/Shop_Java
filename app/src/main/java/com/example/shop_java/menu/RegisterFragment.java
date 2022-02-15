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
import com.example.shop_java.databinding.FragmentRegisterBinding;
import com.example.shop_java.login.model.Address;
import com.example.shop_java.register.model.RegisterRequest;
import com.example.shop_java.register.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        RegisterViewModel registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        FragmentRegisterBinding fragmentRegisterBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_register, container, false);

        fragmentRegisterBinding.setRegisterRequest(new RegisterRequest());
        fragmentRegisterBinding.setAddress(new Address());

        fragmentRegisterBinding.setRegisterViewModel(registerViewModel);


        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RegisterViewModel.REGISTER_RESPONSE.observe(getViewLifecycleOwner(), response -> {

            if (response && isAdded()) {

                Toast.makeText(requireActivity(), "Good!",
                        Toast.LENGTH_SHORT).show();
            } else {


                Toast.makeText(requireActivity(), "Ciota ne to!",
                        Toast.LENGTH_SHORT).show();
            }

        });


    }
}
