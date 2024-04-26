package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ScientificBooks : AppCompatActivity() {

    private lateinit var flagsRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val imageResId = R.drawable.python
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific)

        flagsRV = findViewById(R.id.scientific)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        flagsRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        flagsRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Book 1", "10.99", "Description for Book 1", imageResId.toString()))
        dataList.add(Model("2", "Book 2", "12.99", "Description for Book 2", imageResId.toString()))
        adapter.notifyDataSetChanged()
    }
}