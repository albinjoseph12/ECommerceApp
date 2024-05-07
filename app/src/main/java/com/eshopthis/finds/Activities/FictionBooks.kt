package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class FictionBooks : AppCompatActivity() {

    private lateinit var fictionRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiction)

        fictionRV = findViewById(R.id.id_Fiction)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        fictionRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        fictionRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Fiction Book 1", "8.99", "Description for Fiction Book 1", R.drawable.fiction))
        dataList.add(Model("2", "Fiction Book 2", "9.99", "Description for Fiction Book 2", R.drawable.fifa))
        adapter.notifyDataSetChanged()
    }
}