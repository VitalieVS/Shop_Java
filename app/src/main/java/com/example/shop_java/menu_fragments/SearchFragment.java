package com.example.shop_java.menu_fragments;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_java.R;
import com.example.shop_java.category.model.CategoryModel;
import com.example.shop_java.category.ui.CategoriesAdapter;
import com.example.shop_java.category.ui.CategoryViewModel;
import com.example.shop_java.connection_fragments.NoInternetFragment;

import java.util.List;

public class SearchFragment extends Fragment {

    CategoryViewModel categoryViewModel;

    EditText searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getCategories();

        final RecyclerView recyclerView = requireView().findViewById(R.id.personListRecyclerView);
        final CategoriesAdapter adapter = new CategoriesAdapter();
        recyclerView.setAdapter(adapter);

        searchView = requireView().findViewById(R.id.categoriesSearchView);

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
                new Observer<List<CategoryModel>>() {

                    @Override
                    public void onChanged(List<CategoryModel> categoryModels) {

                        adapter.setList(categoryModels);
                        if (categoryModels.isEmpty() && isAdded()) {
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container,
                                            new NoInternetFragment()).commit();
                }
            }
        });
    }


}
