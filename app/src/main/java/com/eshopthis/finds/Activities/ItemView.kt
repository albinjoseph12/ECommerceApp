package com.eshopthis.finds.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.eshopthis.finds.R
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemView : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var price: TextView
    private lateinit var quantity: TextView
    private lateinit var image_view: ImageView
    private lateinit var increment: ImageButton
    private lateinit var decrement: ImageButton
    private lateinit var addToCart: Button
    private var s_quantity: Int = 1
    private var JerseyPrice: Double = 0.0
    private var s_title: String = ""
    private var s_price: String = ""
    private var s_description: String = ""
    private var s_image: String = ""
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_view)

        title = findViewById(R.id.id_title)
        price = findViewById(R.id.id_price)
        description = findViewById(R.id.id_description)
        image_view = findViewById(R.id.id_image)
        quantity = findViewById(R.id.id_quantity)
        increment = findViewById(R.id.btn_plus)
        decrement = findViewById(R.id.btn_minus)
        addToCart = findViewById(R.id.id_addToCartbtn)

        val data: Intent? = intent
        s_title = data?.getStringExtra("title") ?: ""
        s_price = data?.getStringExtra("price") ?: ""
        s_description = data?.getStringExtra("description") ?: ""
        s_image = data?.getStringExtra("imageData") ?: ""

        try {
            JerseyPrice = s_price.toDouble()
        } catch (e: NumberFormatException) {
            System.err.println(e.message)
        }

        title.text = s_title
        price.text = s_price
        description.text = s_description
        Glide.with(this)
            .load(s_image)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(image_view)

        increment.setOnClickListener {
            s_quantity++
            val yourPrice = s_quantity * JerseyPrice
            price.text = yourPrice.toString()
            quantity.text = s_quantity.toString()
        }

        decrement.setOnClickListener {
            if (s_quantity > 1) {
                s_quantity--
                val yourPrice = s_quantity * JerseyPrice
                price.text = yourPrice.toString()
                quantity.text = s_quantity.toString()
            }
        }

        addToCart.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        try {
            id = (Math.random() * Integer.MAX_VALUE).toInt()

            GlobalScope.launch(Dispatchers.IO) {
                val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "cart_db").allowMainThreadQueries().build()
                val itemDAO = db.itemDao()
                val check = itemDAO.isTitleExists(s_title)
                withContext(Dispatchers.Main) {
                    if (check == 0) {
                        itemDAO.insertItem(Item(id = id, name = s_title, description = s_description, price = JerseyPrice, quantity = s_quantity))
                        Toast.makeText(this@ItemView, "Item added to cart", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ItemView, "Item Already exist in cart", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this@ItemView, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}