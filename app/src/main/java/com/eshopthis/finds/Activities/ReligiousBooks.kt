package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ReligiousBooks : AppCompatActivity() {

    private lateinit var religiousRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_religion)

        religiousRV = findViewById(R.id.id_religion)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        religiousRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        religiousRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Religious Book 1", "10.99", "Description for Religious Book 1", R.drawable.religion))
        dataList.add(Model("2", "Religious Book 2", "12.99", "Description for Religious Book 2", R.drawable.religion))
        adapter.notifyDataSetChanged()
    }
}