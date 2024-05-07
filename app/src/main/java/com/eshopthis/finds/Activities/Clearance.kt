package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class Clearance : AppCompatActivity() {

    private lateinit var clearanceRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clearance)

        clearanceRV = findViewById(R.id.id_Clearance)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 2)
        clearanceRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        clearanceRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Clearance Book 1", "5.99", "Description for Clearance Book 1", R.drawable.clearance))
        dataList.add(Model("2", "Clearance Book 2", "7.99", "Description for Clearance Book 2", R.drawable.java))
        adapter.notifyDataSetChanged()
    }
}