package com.example.shop_java.address;

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

import com.example.shop_java.R;
import com.example.shop_java.address.viewmodel.AddressViewModel;
import com.example.shop_java.connection.NoInternetFragment;
import com.example.shop_java.databinding.FragmentLoggedAddressBinding;
import com.example.shop_java.login.model.Address;
import com.example.shop_java.login.service.UserService;
import com.example.shop_java.menu.UserFragment;

public class LoggedAddress extends Fragment {

    private final Address address;

    private AddressViewModel addressViewModel;

    public LoggedAddress(Address address) {

        this.address = address;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);

        FragmentLoggedAddressBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_logged_address, container, false);

        UserService userService = UserService.getInstance();

        View view = binding.getRoot();

        userService.setContext(requireContext());

        userService.setAddressViewModel(this.addressViewModel);

        binding.setAddress(this.address);

        binding.setUserService(userService);

        ImageView backView = view.findViewById(R.id.backButton);

        backView.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new UserFragment()).commit());

        AddressViewModel.ADDRESS_RESPONSE.observe(getViewLifecycleOwner(),
                addressResponse -> {

                    if (addressResponse == null && isAdded()) {

                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new NoInternetFragment()).commit();

                    } else if (isAdded() && addressResponse.getError().contains("Succ")) {

                        Toast.makeText(requireActivity(),
                                addressResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new UserFragment()).commit();
                    } else if (isAdded()) {

                        Toast.makeText(requireActivity(),
                                addressResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    addressViewModel.resetAddress();

                });

        return view;
    }
}
