package com.eshopthis.finds.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshopthis.finds.Adapter.cartAdapter
import com.eshopthis.finds.R
import com.eshopthis.finds.data.AppDatabase
import com.eshopthis.finds.data.Space
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentCart : Fragment() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartItemTotalPrice: TextView
    private lateinit var cartTotalPrice: TextView
    private lateinit var cartDeliveryCharge: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.id_cartToolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar.title = "My Cart"

        cartItemTotalPrice = view.findViewById(R.id.cartItemTotalPrice)
        cartTotalPrice = view.findViewById(R.id.cartTotalPrice)
        cartDeliveryCharge = view.findViewById(R.id.cartDeliveryCharge)
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)

        getRoomData()

        return view
    }

    private fun getRoomData() {
        GlobalScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getInstance(requireContext())
            val itemDao = db.itemDao()
            val items = itemDao.getAllItems()
            withContext(Dispatchers.Main) {
                cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

                val cartAdapter = cartAdapter(items.toMutableList(), cartItemTotalPrice, cartTotalPrice, cartDeliveryCharge)
                cartRecyclerView.adapter = cartAdapter
                cartRecyclerView.addItemDecoration(Space(5))

                var sum = 0
                val total: Int
                val delivery = 110

                for (item in items) {
                    sum += item.price.toInt() * item.quantity
                }

                total = sum + delivery

                cartItemTotalPrice.text = "$sum $"

                if (sum == 0) {
                    cartTotalPrice.text = "0 $"
                    cartDeliveryCharge.text = "0 $"
                } else {
                    cartTotalPrice.text = "$total $"
                    cartDeliveryCharge.text = "$delivery $"
                }
            }
        }
    }
}