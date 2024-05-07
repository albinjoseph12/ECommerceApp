package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ClassicBooks : AppCompatActivity() {

    private lateinit var customizedJerseyRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_books)

        customizedJerseyRV = findViewById(R.id.id_Classic_Books)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        customizedJerseyRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        customizedJerseyRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Java ", "10.99", "Description for Book 1", R.drawable.classics))
        dataList.add(Model("2", "Java-2", "12.99", "Description for Book 2", R.drawable.classics))
        adapter.notifyDataSetChanged()
    }
}