package com.example.sylhetjerseyhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sylhetjerseyhouse.db.AppDatabase;
import com.example.sylhetjerseyhouse.db.Item;
import com.example.sylhetjerseyhouse.db.ItemDAO;

public class ItemView extends AppCompatActivity {

    TextView title, description, price, quantity;
    ImageView image_view;
    TextView increment, decrement;
    Button addToCart;
    int s_quantity;
    String s_title, s_price, s_description, s_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);




        title = findViewById(R.id.id_title);
        price = findViewById(R.id.id_price);
        description = findViewById(R.id.id_description);
        image_view = findViewById(R.id.id_image);
        quantity = findViewById(R.id.id_quantity);
        increment = findViewById(R.id.id_increment);
        decrement = findViewById(R.id.id_decrement);
        addToCart = findViewById(R.id.id_addToCartbtn);

        Intent data = getIntent();
        s_title = data.getStringExtra("title");
        s_price = data.getStringExtra("price");
        s_description = data.getStringExtra("description");
        s_image = data.getStringExtra("imageData");


        title.setText(s_title);
        price.setText(s_price);
        description.setText(s_description);
        Glide.with(this)
                .load(s_image)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(image_view);


        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentValue = quantity.getText().toString();
                int value = Integer.parseInt(currentValue);
                value++;
                quantity.setText(String.valueOf(value));
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentValue = quantity.getText().toString();
                int value = Integer.parseInt(currentValue);
                if (value > 1) {
                    value--;
                    quantity.setText(String.valueOf(value));
                }
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    s_quantity = Integer.parseInt(quantity.getText().toString());
                    int id = (int) Math.random();

                    AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                    ItemDAO itemDAO = db.itemDAO();
                    Boolean check = itemDAO.is_exist(s_title);
                    if (check == false) {
                        try {
                            itemDAO.insertRecord(new Item(id, s_title, s_price, s_quantity));
                            Toast.makeText(ItemView.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(ItemView.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ItemView.this, "Item Already exist in cart", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(ItemView.this, ""+e, Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}