package com.eshopthis.finds.Activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.eshopthis.finds.Fragments.FragmentCart
import com.eshopthis.finds.Fragments.FragmentCategory
import com.eshopthis.finds.Fragments.FragmentHome
import com.eshopthis.finds.Fragments.Fragment_Settings
import com.eshopthis.finds.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.id_bottomNavigationbar)
        replaceFragment(FragmentHome())

        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
            R.id.id_home -> replaceFragment(FragmentHome())
            R.id.id_category -> replaceFragment(FragmentCategory())
            R.id.id_cart -> replaceFragment(FragmentCart())
            R.id.id_settings -> replaceFragment(Fragment_Settings())
        }
            true
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.id_frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Exit App")
        alertDialog.setMessage("Do you want to exit this application?")
        alertDialog.setPositiveButton("Yes") { _, _ -> finishAffinity() }
        alertDialog.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }
}