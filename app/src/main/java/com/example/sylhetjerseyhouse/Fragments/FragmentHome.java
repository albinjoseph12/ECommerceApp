package com.example.sylhetjerseyhouse.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
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
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.sylhetjerseyhouse.Adapter.Adapter;
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
    private ImageSlider imageSlider;


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
        imageSlider = view.findViewById(R.id.image_slider);

        recyclerViewPopular();
        slideImage();
        getData();


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
        // Determine screen size
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        int spanCount;
        if (dpWidth > 600) { // Adjust this threshold as needed
            spanCount = 3; // For larger screens
        } else {
            spanCount = 2; // For smaller screens
        }

        layoutManager = new GridLayoutManager(getContext(), spanCount);
        flashSaleRV.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();
        adapter = new Adapter(getContext(), dataList);
        flashSaleRV.setAdapter(adapter);
    }



    private void slideImage(){
        ArrayList<SlideModel> imageList = new ArrayList<SlideModel>(); // Create image list

        imageList.add(new SlideModel(R.drawable.image_slide1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slide2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slide3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
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