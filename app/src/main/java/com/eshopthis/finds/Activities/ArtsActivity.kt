package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ArtsActivity : AppCompatActivity() {

    private lateinit var recyclerViewArts: RecyclerView
    private lateinit var adapter: Adapter
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arts)

        recyclerViewArts = findViewById(R.id.recyclerViewArts)
        recyclerViewArts.layoutManager = GridLayoutManager(this, 2)

        adapter = Adapter(this, dataList)
        recyclerViewArts.adapter = adapter

        loadBooks()
    }

    private fun loadBooks() {
        dataList.clear()
        dataList.add(Model("1", "Art Book 1", "$19.99", "Description for Art Book 1", R.drawable.arts))
        dataList.add(Model("2", "Art Book 2", "$29.99", "Description for Art Book 2", R.drawable.arts))
        adapter.notifyDataSetChanged()
    }
}