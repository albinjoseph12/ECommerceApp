package com.eshopthis.finds.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.Adapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class HistoryBooks : AppCompatActivity() {

    private lateinit var historyRV: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyRV = findViewById(R.id.id_History)
        fetchData()
    }

    private fun fetchData() {
        layoutManager = GridLayoutManager(applicationContext, 3)
        historyRV.layoutManager = layoutManager
        adapter = Adapter(this, dataList)
        historyRV.adapter = adapter
        prepareData()
    }

    private fun prepareData() {
        dataList.clear()
        dataList.add(Model("1", "History Book 1", "12.99", "Description for History Book 1", R.drawable.history))
        dataList.add(Model("2", "History Book 2", "14.99", "Description for History Book 2", R.drawable.history))
        adapter.notifyDataSetChanged()
    }
}