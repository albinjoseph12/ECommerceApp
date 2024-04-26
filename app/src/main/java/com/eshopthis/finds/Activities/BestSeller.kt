package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class BestSeller : AppCompatActivity() {

    private lateinit var clubsJerseyRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val imageResId = R.drawable.the_alchemist
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_seller)

        clubsJerseyRV = findViewById(R.id.id_ClubsJerseyRV)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 2)
        clubsJerseyRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        clubsJerseyRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "The Alchemist", "10.99", "Description for The Alchemist", imageResId.toString()))
        dataList.add(Model("2", "The Alchemist-2", "12.99", "Description for The Alchemist-2", imageResId.toString()))
        adapter.notifyDataSetChanged()
    }
}