package com.eshopthis.finds.Fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class FragmentHome : Fragment() {

    private lateinit var flashSaleRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var imageSlider: ImageSlider
    private val dataList = mutableListOf<Model>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        flashSaleRV = view.findViewById(R.id.id_flashSaleRV)
        imageSlider = view.findViewById(R.id.image_slider)

        setupRecyclerView()
        setupImageSlider()
        setupData()

        return view
    }

    private fun setupRecyclerView() {
        val displayMetrics = resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val spanCount = if (dpWidth > 600) 3 else 2

        layoutManager = GridLayoutManager(requireContext(), spanCount)
        flashSaleRV.layoutManager = layoutManager

        adapter = Adapter(requireContext(), dataList)
        flashSaleRV.adapter = adapter
    }

    private fun setupImageSlider() {
        val imageList = ArrayList<SlideModel>().apply {
            add(SlideModel(R.drawable.image_slide1, ScaleTypes.CENTER_CROP))
            add(SlideModel(R.drawable.image_slide2, ScaleTypes.CENTER_CROP))
            add(SlideModel(R.drawable.image_slide3, ScaleTypes.CENTER_CROP))
        }

        imageSlider.setImageList(imageList)
    }

    private fun setupData() {
        dataList.clear()

        // Add items with new images here
        dataList.add(Model("1", "Ruby", "10", "Description for Ruby", R.drawable.ruby.toString()))
        dataList.add(Model("2", "React", "10", "Description for React", R.drawable.react.toString()))
        dataList.add(Model("3", "Java", "10", "Description for Java", R.drawable.java.toString()))
        dataList.add(Model("4", "JavaScript", "10", "Description for JavaScript", R.drawable.javascript.toString()))
        dataList.add(Model("5", "SQL", "10", "Description for SQL", R.drawable.sql.toString()))
        dataList.add(Model("6", "Python", "10", "Description for Python", R.drawable.python.toString()))

        adapter.notifyDataSetChanged()
    }
}