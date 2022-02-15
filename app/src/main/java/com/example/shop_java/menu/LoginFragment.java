package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shop_java.R;
import com.example.shop_java.databinding.BottomSheetForgotPasswordBinding;
import com.example.shop_java.databinding.FragmentLoginBinding;
import com.example.shop_java.login.model.LoginRequest;
import com.example.shop_java.login.service.UserService;
import com.example.shop_java.login.viewmodel.AuthorisationStatus;
import com.example.shop_java.login.viewmodel.LoginViewModel;
import com.example.shop_java.security.reset.model.PasswordRequest;
import com.example.shop_java.security.reset.viewmodel.ResetPasswordViewModel;
import com.example.shop_java.security.service.SecurityService;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private BottomSheetDialog bottomSheetDialog;

    private BottomSheetForgotPasswordBinding bindingSheet;

    private ResetPasswordViewModel resetPasswordViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        UserService userService = UserService.getInstance();
        userService.setContext(requireActivity());

        TextView forgotPassword = requireActivity().findViewById(R.id.forgotPassword);

        TextView registerNow = requireActivity().findViewById(R.id.registerNow);

        registerNow.setOnClickListener(listener -> {

            Fragment registerFragment = new RegisterFragment();

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).replace(R.id.fragment_container,
                    registerFragment).commit();
        });

        bottomSheetDialog =
                new BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme);

        SecurityService securityService = SecurityService.getInstance();

        forgotPassword.setOnClickListener(listener -> {

            bindingSheet = DataBindingUtil.inflate(
                    LayoutInflater.from(requireActivity()),
                    R.layout.bottom_sheet_forgot_password,
                    null,
                    false);

            bindingSheet.setSecurityService(securityService);

            bindingSheet.setPasswordRequest(new PasswordRequest());

            bindingSheet.setResetPasswordViewModel(resetPasswordViewModel);

            bindingSheet.backButton.setOnClickListener(backButtonListener ->
                    bottomSheetDialog.cancel());

            bindingSheet.backText.setOnClickListener(backButtonListener ->
                    bottomSheetDialog.cancel());

            ResetPasswordViewModel.RESET_RESPONSE.observe(getViewLifecycleOwner(),
                    resetResponse -> {

                        if (resetResponse == null && isAdded()) {

                            Toast.makeText(requireActivity(), "No internet!",
                                    Toast.LENGTH_SHORT).show();
                        } else if (isAdded()) {

                            Toast.makeText(requireActivity(),
                                    resetResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        resetPasswordViewModel.setResetResponse();
                        bottomSheetDialog.cancel();
                    });
            bottomSheetDialog.setContentView(bindingSheet.bottomSheetProductContainer);
            bottomSheetDialog.show();
        });


        LoginViewModel.LOGIN_STATUS.observe(getViewLifecycleOwner(), status -> {

            if (status.equals(AuthorisationStatus.SUCCESS) && isAdded()) {

                userService.setAuthorised(true, LoginViewModel.TOKEN.getValue(),
                        LoginViewModel.LOGIN.getValue());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new UserFragment()).commit();
            }

            if (isAdded() && status.equals(AuthorisationStatus.FAILED)) {
                Toast.makeText(requireActivity(),
                        "Wrong credentials!", Toast.LENGTH_SHORT).show();
            }

            if (isAdded() && status.equals(AuthorisationStatus.LOGOUT)) {
                Toast.makeText(requireActivity(),
                        "Session expired!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        resetPasswordViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);

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