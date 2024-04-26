package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class ReligiousBooks : AppCompatActivity() {

    private lateinit var nationalJerseyRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val imageResId = R.drawable.scala
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_religion)

        nationalJerseyRV = findViewById(R.id.id_religion)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 2)
        nationalJerseyRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        nationalJerseyRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "Book 1", "10.99", "Description for Book 1", imageResId.toString()))
        dataList.add(Model("2", "Book 2", "12.99", "Description for Book 2", imageResId.toString()))
        adapter.notifyDataSetChanged()
    }
}