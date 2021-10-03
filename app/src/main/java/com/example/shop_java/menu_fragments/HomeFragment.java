package com.example.shop_java.menu_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.connection_fragments.NoInternetFragment;
import com.example.shop_java.promotion.model.PromotionModel;
import com.example.shop_java.promotion.ui.PromotionViewModel;
import com.example.shop_java.promotion.ui.PromotionsAdapter;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    PromotionViewModel promotionViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        promotionViewModel = ViewModelProviders.of(this).get(PromotionViewModel.class);

        promotionViewModel.getPromotions();

        RecyclerView recyclerView = requireView().findViewById(R.id.recycler);
        final PromotionsAdapter adapter = new PromotionsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        PromotionViewModel.promotionsMutableLiveData.observe(requireActivity(), new Observer<List<PromotionModel>>() {
            @Override
            public void onChanged(List<PromotionModel> promotionModels) {
                adapter.setList(promotionModels);
                Log.d("added", String.valueOf(isAdded()));
                if (promotionModels.isEmpty() && isAdded()) {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new NoInternetFragment()).commit();

                }
            }
        });
    }
}
