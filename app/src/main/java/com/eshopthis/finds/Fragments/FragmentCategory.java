package com.eshopthis.finds.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshopthis.finds.Activities.ArtsActivity;
import com.eshopthis.finds.Activities.BestSeller;
import com.eshopthis.finds.Activities.ClassicBooks;
import com.eshopthis.finds.Activities.Clearance;
import com.eshopthis.finds.Activities.ScientificBooks;
import com.eshopthis.finds.Activities.FictionBooks;
import com.eshopthis.finds.Activities.ReligiousBooks;
import com.eshopthis.finds.Activities.HistoryBooks;
import com.eshopthis.finds.Activities.AnimeBooks;
import com.eshopthis.finds.R;
import com.eshopthis.finds.Adapter.CategoryAdapter;
import com.eshopthis.finds.db.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentCategory extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<CategoryItem> categoryItems;

    public FragmentCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        initializeCategoryItems();

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryAdapter = new CategoryAdapter(getContext(), categoryItems, this);
        categoryRecyclerView.setAdapter(categoryAdapter);

        return view;
    }

    private void initializeCategoryItems() {
        categoryItems = new ArrayList<>();
        categoryItems.add(new CategoryItem("Clearance", R.drawable.clearance));
        categoryItems.add(new CategoryItem("Best Sellers", R.drawable.best_seller));
        categoryItems.add(new CategoryItem("Classics", R.drawable.classics));
        categoryItems.add(new CategoryItem("Scientific", R.drawable.scientific));
        categoryItems.add(new CategoryItem("Fiction", R.drawable.fiction));
        categoryItems.add(new CategoryItem("Religion", R.drawable.religion));
        categoryItems.add(new CategoryItem("History", R.drawable.history));
        categoryItems.add(new CategoryItem("Anime", R.drawable.anime));
        categoryItems.add(new CategoryItem("Arts", R.drawable.arts));
        // ... Add all the other categories similarly
    }

    @Override
    public void onCategoryClick(CategoryItem categoryItem) {
        Intent intent = null;
        switch (categoryItem.getName()) {
            case "Clearance":
                intent = new Intent(getContext(), Clearance.class);
                break;
            case "Best Sellers":
                intent = new Intent(getContext(), BestSeller.class);
                break;
            case "Classics":
                intent = new Intent(getContext(), ClassicBooks.class);
                break;
            case "Scientific":
                intent = new Intent(getContext(), ScientificBooks.class);
                break;
            case "Fiction":
                intent = new Intent(getContext(), FictionBooks.class);
                break;
            case "Religion":
                intent = new Intent(getContext(), ReligiousBooks.class);
                break;
            case "History":
                intent = new Intent(getContext(), HistoryBooks.class);
                break;
            case "Anime":
                intent = new Intent(getContext(), AnimeBooks.class);
                break;
            case "Arts":
                intent = new Intent(getContext(), ArtsActivity.class);
                break;
            // ... Handle all the other cases similarly
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
