package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ScientificBooks : AppCompatActivity() {

    private lateinit var scientificRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific)

        scientificRV = findViewById(R.id.scientific)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        scientificRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        scientificRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Scientific Book 1", "15.99", "Description for Scientific Book 1", R.drawable.scientific))
        dataList.add(Model("2", "Scientific Book 2", "17.99", "Description for Scientific Book 2", R.drawable.scala))
        adapter.notifyDataSetChanged()
    }
}