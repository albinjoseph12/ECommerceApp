package com.eshopthis.finds.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.R
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class cartAdapter(
    private val items: MutableList<Item>,
    private val cartItemTotalPrice: TextView,
    private val cartTotalPrice: TextView,
    private val cartDeliveryCharge: TextView
) : RecyclerView.Adapter<cartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_format, parent, false)
        return CartViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.cartItemTitle.text = item.name
        holder.cartItemPrice.text = item.price.toString()
        holder.cartItemQuantity.text = item.quantity.toString()

        holder.increment.setOnClickListener {
            item.quantity++
            notifyDataSetChanged()
            updatePrice()
        }

        holder.decrement.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyDataSetChanged()
                updatePrice()
            }
        }

        holder.delete.setOnClickListener {
            val db = AppDatabase.getInstance(holder.cartItemTitle.context)
            val itemDao = db.itemDao()
            GlobalScope.launch(Dispatchers.IO) {
                itemDao.deleteItemById(item.id)
                withContext(Dispatchers.Main) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                    updatePrice()
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartItemTitle: TextView = itemView.findViewById(R.id.cartItemTitle)
        val cartItemPrice: TextView = itemView.findViewById(R.id.cartItemPrice)
        val cartItemQuantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val cartItemLayout: LinearLayout = itemView.findViewById(R.id.cartItemLayout)
        val delete: ImageButton = itemView.findViewById(R.id.cartItemDeleteBtn)
        val increment: Button = itemView.findViewById(R.id.id_cartQuantityIncrement)
        val decrement: Button = itemView.findViewById(R.id.id_cartQuantityDecrement)
    }

    private fun updatePrice() {
        var sum = 0.0
        val total: Double
        val delivery = 110.0

        for (item in items) {
            sum += item.price * item.quantity
        }

        total = sum + delivery

        cartItemTotalPrice.text = "%.2f tk".format(sum)

        if (sum == 0.0) {
            cartTotalPrice.text = "0.00 tk"
            cartDeliveryCharge.text = "0.00 tk"
        } else {
            cartTotalPrice.text = "%.2f tk".format(total)
            cartDeliveryCharge.text = "%.2f tk".format(delivery)
        }
    }
}