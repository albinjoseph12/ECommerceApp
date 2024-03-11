package com.eshopthis.finds.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.eshopthis.finds.Adapter.Adapter;
//import com.ecommerce.shopping.R;
import com.eshopthis.finds.R;
import com.eshopthis.finds.db.Model;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    RecyclerView flashSaleRV;
    Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;
    private List<Model> dataList;
    private ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        flashSaleRV = view.findViewById(R.id.id_flashSaleRV);
        imageSlider = view.findViewById(R.id.image_slider);

        setupRecyclerView();
        setupImageSlider();
        setupData();

        return view;
    }

    private void setupRecyclerView() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int spanCount = dpWidth > 600 ? 3 : 2;

        layoutManager = new GridLayoutManager(getContext(), spanCount);
        flashSaleRV.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();
        adapter = new Adapter(getContext(), dataList);
        flashSaleRV.setAdapter(adapter);
    }

    private void setupImageSlider() {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image_slide1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slide2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slide3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
    }

    private void setupData() {
        dataList.clear();

        // Add items with new images here
        dataList.add(new Model("1", "Ruby", "10", "Description for Ruby", R.drawable.ruby));
        dataList.add(new Model("2", "React", "10", "Description for React", R.drawable.react));
        dataList.add(new Model("3", "Java", "10", "Description for Java", R.drawable.java));
        dataList.add(new Model("4", "JavaScript", "10", "Description for JavaScript", R.drawable.javascript));
        dataList.add(new Model("5", "SQL", "10", "Description for SQL", R.drawable.sql));
        dataList.add(new Model("6", "Python", "10", "Description for Python", R.drawable.python));

        adapter.notifyDataSetChanged();
    }
}
