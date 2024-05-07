package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class AnimeBooks : AppCompatActivity() {

    private lateinit var winterClothesRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)

        winterClothesRV = findViewById(R.id.id_Anime)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        winterClothesRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        winterClothesRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Anime", "10.99", "Description for Book 1", R.drawable.anime))
        dataList.add(Model("2", "Anime-2", "12.99", "Description for Book 2", R.drawable.anime))
        adapter.notifyDataSetChanged()
    }
}