package com.example.shop_java.menu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.category.ui.adapter.CategoriesAdapter;
import com.example.shop_java.category.ui.viewmodel.CategoryViewModel;
import com.example.shop_java.connection.NoInternetFragment;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        CategoryViewModel categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getCategories();

        final RecyclerView recyclerView = requireView().findViewById(R.id.categoryListRecyclerView);
        final CategoriesAdapter adapter = new CategoriesAdapter();
        recyclerView.setAdapter(adapter);

        EditText searchView = requireView().findViewById(R.id.categoriesSearchView);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // this method is empty because we only use afterTextChanged
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // this method is empty because we only use afterTextChanged
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);
            }
        });

        CategoryViewModel.categoriesMutableLiveData.observe(requireActivity(),
                categoryModels -> {

                    adapter.setList(categoryModels);
                    if (categoryModels.isEmpty() && isAdded()) {
                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container,
                                        new NoInternetFragment()).commit();
                    }
                });
    }

}
