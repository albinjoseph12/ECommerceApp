package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class HistoryBooks : AppCompatActivity() {

    private lateinit var snakersRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val imageResId = R.drawable.react
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        snakersRV = findViewById(R.id.id_History)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        snakersRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        snakersRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "History 1", "10.99", "Description for Book 1", imageResId.toString()))
        dataList.add(Model("2", "History 2", "12.99", "Description for Book 2", imageResId.toString()))
        adapter.notifyDataSetChanged()
    }
}