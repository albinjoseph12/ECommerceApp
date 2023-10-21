package com.example.sylhetjerseyhouse.Activities;

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
import com.example.sylhetjerseyhouse.Adapter.Adapter;
import com.example.sylhetjerseyhouse.R;
import com.example.sylhetjerseyhouse.db.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClubsJersey extends AppCompatActivity {

    RecyclerView clubsJerseyRV;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;
    private String url = "https://food-buzz.000webhostapp.com/clubs_jersey.php";
    private List<Model> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_jersey);

        clubsJerseyRV = findViewById(R.id.id_ClubsJerseyRV);
        fetchData();

    }






    private void fetchData() {
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        clubsJerseyRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(this, dataList);
        clubsJerseyRV.setAdapter(adapter);
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
                            String url2 = object.getString("image");

                            String urlImage = "https://food-buzz.000webhostapp.com/popular_images/"+url2;

                            model = new Model(id, title, price, description, urlImage);
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