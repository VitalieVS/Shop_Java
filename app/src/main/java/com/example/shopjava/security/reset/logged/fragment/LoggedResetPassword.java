package com.example.shopjava.security.reset.logged.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopjava.R;
import com.example.shopjava.databinding.FragmentLoggedResetPasswordBinding;
import com.example.shopjava.menu.UserFragment;
import com.example.shopjava.security.reset.logged.model.PasswordLoggedResetRequest;
import com.example.shopjava.security.reset.logged.viewmodel.ResetPasswordViewModel;
import com.example.shopjava.security.service.SecurityService;

public class LoggedResetPassword extends Fragment {

    private ResetPasswordViewModel resetPasswordViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        resetPasswordViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);

        SecurityService securityService = SecurityService.getInstance();

        securityService.setResetPasswordViewModel(resetPasswordViewModel);

        FragmentLoggedResetPasswordBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_logged_reset_password, container, false);

        View view = binding.getRoot();

        SharedPreferences settings = requireContext().getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        securityService.setToken("Bearer_" + settings.getString("token", ""));

        binding.setResetRequest(new PasswordLoggedResetRequest());

        binding.setSecurityService(securityService);

        ResetPasswordViewModel.RESET_RESPONSE.observe(getViewLifecycleOwner(),
                resetResponse -> {

                    if (resetResponse == null && isAdded()) {

                        Toast.makeText(requireActivity(), "No internet!",
                                Toast.LENGTH_SHORT).show();
                    } else if (isAdded()) {

                        Toast.makeText(requireActivity(),
                                resetResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new UserFragment()).commit();
                    }

                    resetPasswordViewModel.setResetResponse();
                });

        ImageView backView = view.findViewById(R.id.backButton);

        backView.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new UserFragment()).commit());

        return view;
    }
}
