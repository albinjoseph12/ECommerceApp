package com.eshopthis.finds.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.eshopthis.finds.Adapter.Adapter;
import com.eshopthis.finds.db.Model;
//import com.ecommerce.shopping.R;
import com.eshopthis.finds.R;

import java.util.ArrayList;
import java.util.List;

public class BestSeller extends AppCompatActivity {

    RecyclerView clubsJerseyRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int imageResId = R.drawable.the_alchemist; // Corrected syntax
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);

        clubsJerseyRV = findViewById(R.id.id_ClubsJerseyRV);
        fetchData();
    }

    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        clubsJerseyRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        clubsJerseyRV.setAdapter(adapter);
        prepareData(); // Use prepareData instead of getData
    }

    private void prepareData() {
        // Simulate adding items. In a real app, this data might come from a local database or file
        dataList.clear();
        dataList.add(new Model("1", "The Alchemist", "10.99", "Description for The Alchemist", imageResId));
        dataList.add(new Model("2", "The Alchemist-2", "12.99", "Description for The Alchemist-2", imageResId));
        // ... add more items as needed
        adapter.notifyDataSetChanged();
    }
}
