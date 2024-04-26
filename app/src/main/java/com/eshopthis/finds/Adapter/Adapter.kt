package com.eshopthis.finds.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eshopthis.finds.Activities.ItemView
import com.eshopthis.finds.R
import com.eshopthis.finds.data.Model

class Adapter(
        private val context: Context,
        private val dataList: List<Model>
) : RecyclerView.Adapter<Adapter.ImageViewHolder>() {

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pattern, parent, false)
    return ImageViewHolder(view)
}

override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    val temp = dataList[position]

    holder.tvTitle.text = temp.title
    holder.tvPrice.text = "${temp.price} $"

    if (!temp.imageUrl.isNullOrEmpty()) {
        Glide.with(context).load(temp.imageUrl).into(holder.imageView)
    } else if (temp.imageResId != 0) {
        holder.imageView.setImageResource(temp.imageResId)
    }

    holder.constraintLayout.setOnClickListener {
        try {
            val intent = Intent(context, ItemView::class.java)
            intent.putExtra("title", temp.title)
            intent.putExtra("price", temp.price)
            intent.putExtra("description", temp.description)

            if (!temp.imageUrl.isNullOrEmpty()) {
                intent.putExtra("imageData", temp.imageUrl)
            } else if (temp.imageResId != 0) {
                intent.putExtra("imageData", temp.imageResId)
            }

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}

override fun getItemCount(): Int = dataList.size

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.findViewById(R.id.id_itemTitle)
    val tvPrice: TextView = itemView.findViewById(R.id.id_itemPrice)
    val imageView: ImageView = itemView.findViewById(R.id.id_itemImage)
    val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayoutItem)
}
}