package com.example.shop_java.security.service;

public class SecurityService {

    private static SecurityService securityService;

    public static SecurityService getInstance() {

        if (securityService == null) {
            securityService = new SecurityService();
        }

        return securityService;
    }


}
