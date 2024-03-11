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

public class ArtsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Model> dataList; // Replace with your actual book model list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts); // Make sure you have this layout

        recyclerView = findViewById(R.id.recyclerViewArts); // Ensure you have a RecyclerView in activity_arts.xml
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Or any other LayoutManager
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        recyclerView.setAdapter(adapter);

        loadBooks(); // Method to load books into the list
    }

    private void loadBooks() {
        // Here, you would load your books into bookList.
        // This could be a static list, fetched from a database, or loaded from an API.
        // Correct use of the constructor with an image resource ID
        dataList.add(new Model("1", "Art Book 1", "$19.99", "Description", R.drawable.art_book_1));
        // Example
        // Add more books as needed

        adapter.notifyDataSetChanged();
    }
}
