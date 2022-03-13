package com.example.shopjava.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopjava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SuccessFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        BottomNavigationView bottomNavigationView =
                requireActivity().findViewById(R.id.bottom_navigation);


        FloatingActionButton floatingActionButton =
                requireView().findViewById(R.id.jumpToOrders);

        floatingActionButton.setOnClickListener(l -> {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new UserFragment()).commit();

            bottomNavigationView.setSelectedItemId(R.id.login);
            bottomNavigationView.setVisibility(View.VISIBLE);
        });


    }
}

