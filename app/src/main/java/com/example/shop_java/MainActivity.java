package com.example.shop_java;


import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shop_java.login.service.UserService;
import com.example.shop_java.menu.CartFragment;
import com.example.shop_java.menu.HomeFragment;
import com.example.shop_java.menu.LoginFragment;
import com.example.shop_java.menu.ReviewsFragment;
import com.example.shop_java.menu.SearchFragment;
import com.example.shop_java.menu.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);


        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container, new HomeFragment()).commit();

        userService = UserService.getInstance();
        userService.setContext(this);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.reviews:
                    selectedFragment = new ReviewsFragment();
                    break;
                case R.id.login:

                    if (userService.isAuthorised() && userService.getRememberMe()) {

                        selectedFragment = new UserFragment();
                    } else {

                        selectedFragment = new LoginFragment();
                    }

                    break;
                case R.id.cart:

                    getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .add(R.id.fragment_container, CartFragment.class, null)
                                .commit();

                    selectedFragment = new CartFragment();
                    break;
                default:
                    throw new UnknownError("Unknown value");
            }

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        });

    }
}