package com.example.shop_java.menu_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.category.model.CategoryModel;
import com.example.shop_java.category.ui.CategoriesAdapter;
import com.example.shop_java.category.ui.CategoryViewModel;
import com.example.shop_java.connection_fragments.NoInternetFragment;
import com.example.shop_java.promotion.model.PromotionModel;
import com.example.shop_java.promotion.ui.PromotionViewModel;
import com.example.shop_java.promotion.ui.PromotionsAdapter;

import java.util.List;

public class SearchFragment extends Fragment {

    CategoryViewModel categoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getCategories();

        RecyclerView recyclerView = requireView().findViewById(R.id.personListRecyclerView);
        final CategoriesAdapter adapter = new CategoriesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        CategoryViewModel.categoriesMutableLiveData.observe(requireActivity(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {
                adapter.setList(categoryModels);
                if (categoryModels.isEmpty() && isAdded()) {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new NoInternetFragment()).commit();

                }
            }
        });
    }


}
