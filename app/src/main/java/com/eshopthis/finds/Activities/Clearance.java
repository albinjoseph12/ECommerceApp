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

public class Clearance extends AppCompatActivity {

    RecyclerView clubsKeyRingRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearance);

        clubsKeyRingRV = findViewById(R.id.id_Clearance);
        fetchData();
    }

    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        clubsKeyRingRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        clubsKeyRingRV.setAdapter(adapter);
        prepareData();
    }

    private void prepareData() {
        // Simulate adding items, ensure you have these drawable resources
        dataList.clear();
        dataList.add(new Model("1", "Clearance Item 1", "9.99", "Description for Clearance Item 1", R.drawable.cplus));
        dataList.add(new Model("2", "Clearance Item 2", "11.99", "Description for Clearance Item 2", R.drawable.cplus));
        // Add more items as needed
        adapter.notifyDataSetChanged();
    }
}
