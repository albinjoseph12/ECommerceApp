package com.eshopthis.finds.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.eshopthis.finds.Adapter.Adapter;
import com.eshopthis.finds.R;
import com.eshopthis.finds.db.Model;

import java.util.ArrayList;
import java.util.List;

public class ArtsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewArts;
    private Adapter adapter;
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts);

        recyclerViewArts = findViewById(R.id.recyclerViewArts);
        recyclerViewArts.setLayoutManager(new GridLayoutManager(this, 2)); // Adjust the number of columns as needed

        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        recyclerViewArts.setAdapter(adapter);

        loadBooks();
    }

    private void loadBooks() {
        dataList.clear();
        dataList.add(new Model("1", "Art Book 1", "$19.99", "Description for Art Book 1", R.drawable.arts));
        dataList.add(new Model("2", "Art Book 2", "$29.99", "Description for Art Book 2", R.drawable.arts));
        // Ensure you have drawable resources named art_book_1 and art_book_2 in your drawable folder
        // Add more books as needed
        adapter.notifyDataSetChanged();
    }
}
