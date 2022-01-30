package com.example.shop_java.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.connection.NoInternetFragment;
import com.example.shop_java.promotion.ui.adapter.PromotionsAdapter;
import com.example.shop_java.promotion.ui.viewmodel.PromotionViewModel;

public class HomeFragment extends Fragment {

    PromotionViewModel promotionViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        promotionViewModel = new ViewModelProvider(this).get(PromotionViewModel.class);

        promotionViewModel.getPromotions();

        RecyclerView recyclerView = requireView().findViewById(R.id.promotionRecycler);
        final PromotionsAdapter adapter = new PromotionsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        PromotionViewModel.promotionMutableLiveData.observe(requireActivity(), promotionModels -> {
            adapter.setList(promotionModels);
            if (promotionModels.isEmpty() && isAdded()) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NoInternetFragment()).commit();

            }
        });
    }
}
