package com.eshopthis.finds.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eshopthis.finds.Adapter.Adapter;
import com.eshopthis.finds.db.Model;
import com.eshopthis.finds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClubsKeyRing extends AppCompatActivity {

    RecyclerView clubsKeyRingRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;
    private String url = "https://food-buzz.000webhostapp.com/clubs_key_ring.php";
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);

        clubsKeyRingRV = findViewById(R.id.id_Best_Seller);
        fetchData();

    }





    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        clubsKeyRingRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        clubsKeyRingRV.setAdapter(adapter);
        getData();
    }

    private void getData() {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dataList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String title = object.getString("title");
                            String price = object.getString("price");
                            String description = object.getString("description");

                            // Use the local image instead of the one from the URL
                            int imageResId = R.drawable.sql; // Make sure sql.jpg is in the res/drawable folder and is named as sql.jpg

                            model = new Model(id, title, price, description, imageResId);
                            dataList.add(model);
                            adapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }


}