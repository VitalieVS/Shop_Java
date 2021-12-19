package com.example.shop_java.menu_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shop_java.R;

public class CartFragment extends Fragment {

    //CartViewModel cartViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        // cartViewModel.getPromotions();

//        RecyclerView recyclerView = requireView().findViewById(R.id.recycler);
//        final PromotionsAdapter adapter = new PromotionsAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
//
//        PromotionViewModel.promotionMutableLiveData.observe(requireActivity(), new Observer<List<PromotionModel>>() {
//            @Override
//            public void onChanged(List<PromotionModel> promotionModels) {
//                adapter.setList(promotionModels);
//                if (promotionModels.isEmpty() && isAdded()) {
//                    requireActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragment_container, new NoInternetFragment()).commit();
//
//                }
//            }
//        });
    }
}