package com.eshopthis.finds.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

//import com.ecommerce.shopping.R;
import com.eshopthis.finds.R;
import com.eshopthis.finds.db.AppDatabase;
import com.eshopthis.finds.db.Item;
import com.eshopthis.finds.db.ItemDAO;
import com.eshopthis.finds.db.Space;
import com.eshopthis.finds.db.cartAdapter;

import java.util.List;


public class FragmentCart extends Fragment {


    RecyclerView cartRecyclerView;
    TextView cartItemTotalPrice, cartTotalPrice, cartDeliveryCharge;


    public FragmentCart() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        Toolbar toolbar = view.findViewById(R.id.id_cartToolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("My Cart");


        cartItemTotalPrice = view.findViewById(R.id.cartItemTotalPrice);
        cartTotalPrice = view.findViewById(R.id.cartTotalPrice);
        cartDeliveryCharge = view.findViewById(R.id.cartDeliveryCharge);
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);

        getRoomData();

        return view;
    }


    private void getRoomData() {
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ItemDAO itemDAO = db.itemDAO();


        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Item> items = itemDAO.getAll();

        cartAdapter cartAdapter = new cartAdapter(items, cartItemTotalPrice, cartTotalPrice, cartDeliveryCharge);
        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.addItemDecoration(new Space(5));


        int sum = 0, total = 0, delivery = 110;
        for (int i = 0; i < items.size(); i++) {
            sum = sum + (Integer.parseInt(items.get(i).getPrice()) * items.get(i).getQuantity());
        }
        total += sum + delivery;

        cartItemTotalPrice.setText(String.valueOf(sum) + " $");

        if (sum == 0) {
            cartTotalPrice.setText("0 $");
            cartDeliveryCharge.setText("0 $");
        } else {
            cartTotalPrice.setText(String.valueOf(total) + " $");
            cartDeliveryCharge.setText(String.valueOf(delivery) + " $");
        }


    }


}