package com.eshopthis.finds.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.eshopthis.finds.Adapter.Adapter;
//import com.ecommerce.shopping.R;
import com.eshopthis.finds.R;

import com.eshopthis.finds.db.Model;

import java.util.ArrayList;
import java.util.List;

public class FictionBooks extends AppCompatActivity {

    RecyclerView jogersRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int imageResId = R.drawable.cplus; // Resource ID for your local image
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiction);

        jogersRV = findViewById(R.id.id_Fiction);
        fetchData();
    }

    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        jogersRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        jogersRV.setAdapter(adapter);
        prepareData(); // Use prepareData instead of getData
    }

    private void prepareData() {
        // Simulate adding items. In a real app, this data might come from a local database or file
        dataList.clear();
        dataList.add(new Model("1", "Fiction 1", "10.99", "Description for Book 1", imageResId));
        dataList.add(new Model("2", "Fiction 2", "12.99", "Description for Book 2", imageResId));
        // ... add more items as needed
        adapter.notifyDataSetChanged();
    }
}