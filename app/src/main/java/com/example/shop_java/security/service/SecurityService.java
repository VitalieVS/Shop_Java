package com.example.shop_java.security.service;

import com.example.shop_java.security.reset.logged.model.PasswordLoggedResetRequest;
import com.example.shop_java.security.reset.logged.viewmodel.ResetPasswordViewModel;

public class SecurityService {

    private static SecurityService securityService;

    String token;
    private ResetPasswordViewModel resetPasswordViewModel;

    public static SecurityService getInstance() {

        if (securityService == null) {
            securityService = new SecurityService();
        }

        return securityService;
    }

    public void resetLoggedPassword(PasswordLoggedResetRequest passwordLoggedResetRequest) {

        this.resetPasswordViewModel.postResetLoggedPassword(this.token,
                passwordLoggedResetRequest);

    }

    public void setToken(String token) {

        this.token = token;
    }

    public void setResetPasswordViewModel(ResetPasswordViewModel resetPasswordViewModel) {

        this.resetPasswordViewModel = resetPasswordViewModel;
    }


}
