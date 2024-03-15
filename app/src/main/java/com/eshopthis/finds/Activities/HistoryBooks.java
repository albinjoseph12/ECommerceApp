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

public class HistoryBooks extends AppCompatActivity {

    RecyclerView snakersRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int imageResId = R.drawable.react; // Resource ID for your local image
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        snakersRV = findViewById(R.id.id_History);//
        fetchData();
    }

    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        snakersRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        snakersRV.setAdapter(adapter);
        prepareData(); // Use prepareData instead of getData
    }

    private void prepareData() {
        // Simulate adding items. In a real app, this data might come from a local database or file
        dataList.clear();
        dataList.add(new Model("1", "History 1", "10.99", "Description for Book 1", imageResId));
        dataList.add(new Model("2", "History 2", "12.99", "Description for Book 2", imageResId));
        // ... add more items as needed
        adapter.notifyDataSetChanged();
    }
}