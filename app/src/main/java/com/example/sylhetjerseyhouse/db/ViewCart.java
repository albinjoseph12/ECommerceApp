package com.example.sylhetjerseyhouse.db;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sylhetjerseyhouse.R;

import java.util.List;

public class ViewCart extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    TextView cartItemTotalPrice, cartTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        cartItemTotalPrice = findViewById(R.id.cartItemTotalPrice);
        cartTotalPrice = findViewById(R.id.cartTotalPrice);


        getRoomData();


    }

    private void getRoomData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ItemDAO itemDAO = db.itemDAO();

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> items = itemDAO.getAll();

        cartAdapter cartAdapter = new cartAdapter(items, cartItemTotalPrice, cartTotalPrice);
        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.addItemDecoration(new Space(5));


        int sum = 0, total=0, delivery=100;
        for(int i=0; i<items.size(); i++){
            sum = sum + (Integer.parseInt(items.get(i).getPrice()) * items.get(i).getQuantity());
        }
        total += sum + 100;
        cartItemTotalPrice.setText(String.valueOf(sum));
        cartTotalPrice.setText(String.valueOf(total));


    }
}