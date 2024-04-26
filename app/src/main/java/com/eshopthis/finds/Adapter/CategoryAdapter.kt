package com.eshopthis.finds.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.R
import com.eshopthis.finds.data.CategoryItem

class CategoryAdapter(
        private val context: Context,
        private val categoryItems: List<CategoryItem>,
        private val onCategoryClickListener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
    return ViewHolder(view)
}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = categoryItems[position]
    holder.categoryName.text = item.name
    holder.categoryImage.setImageResource(item.imageResourceId)

    holder.itemView.setOnClickListener {
        onCategoryClickListener.onCategoryClick(item)
    }
}

override fun getItemCount(): Int = categoryItems.size

inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
    val categoryName: TextView = itemView.findViewById(R.id.categoryName)
}

interface OnCategoryClickListener {
    fun onCategoryClick(categoryItem: CategoryItem)
}
}