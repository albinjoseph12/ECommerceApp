package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class Clearance : AppCompatActivity() {

    private lateinit var clubsKeyRingRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clearance)

        clubsKeyRingRV = findViewById(R.id.id_Clearance)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        clubsKeyRingRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        clubsKeyRingRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Clearance Item 1", "9.99", "Description for Clearance Item 1", R.drawable.cplus.toString()))
        dataList.add(Model("2", "Clearance Item 2", "11.99", "Description for Clearance Item 2", R.drawable.cplus.toString()))
        adapter.notifyDataSetChanged()
    }
}