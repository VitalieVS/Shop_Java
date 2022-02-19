package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shop_java.R;
import com.example.shop_java.databinding.FragmentStepRegisterBinding;
import com.example.shop_java.register.adapter.ViewPagerAdapter;
import com.example.shop_java.register.fragments.AddressRegisterInfo;
import com.example.shop_java.register.fragments.GeneralRegisterInfo;
import com.example.shop_java.register.fragments.SecurityRegisterInfo;
import com.example.shop_java.register.service.RegisterService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private TabLayout tabLayout;

    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentStepRegisterBinding fragmentStepRegisterBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_step_register, container,
                        false);

        tabLayout = fragmentStepRegisterBinding.tabLayout.findViewById(R.id.tabLayout);

        viewPager2 = fragmentStepRegisterBinding.viewPager.findViewById(R.id.viewPager);

        viewPager2.setOffscreenPageLimit(1);

        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity()
                .getSupportFragmentManager(), requireActivity().getLifecycle());

        adapter.addFragment(new GeneralRegisterInfo(), "General");
        adapter.addFragment(new AddressRegisterInfo(), "Address");
        adapter.addFragment(new SecurityRegisterInfo(), "Security");

        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setAdapter(adapter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(adapter.getFragmentTitleList().get(position))).attach();

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TextView tv = (TextView) LayoutInflater.from(
                    fragmentStepRegisterBinding.getRoot().getContext())
                    .inflate(R.layout.custom_tab, null);

            Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(tv);

        }

        RegisterService registerService = RegisterService.getInstance();

        registerService.setTabLayout(tabLayout);

        registerService.setContext(getContext());

        fragmentStepRegisterBinding.setRegisterService(registerService);

        return fragmentStepRegisterBinding.getRoot();

    }


}
