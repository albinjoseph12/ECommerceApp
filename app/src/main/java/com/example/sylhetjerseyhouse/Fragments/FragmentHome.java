package com.example.sylhetjerseyhouse.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sylhetjerseyhouse.R;
import com.example.sylhetjerseyhouse.db.Model;
import com.example.sylhetjerseyhouse.db.SetDataActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {


    RecyclerView flashSaleRV;
    Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;
    private String url = "https://food-buzz.000webhostapp.com/flash_sale.php";
    private List<Model> dataList;
    Button dataUpload;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        flashSaleRV = view.findViewById(R.id.id_flashSaleRV);
        dataUpload = view.findViewById(R.id.id_upload);
        recyclerViewPopular();

        dataUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getContext(), SetDataActivity.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }








    private void recyclerViewPopular() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        flashSaleRV.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        adapter = new Adapter(getContext(), dataList);


        flashSaleRV.setAdapter((RecyclerView.Adapter) adapter);
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
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}