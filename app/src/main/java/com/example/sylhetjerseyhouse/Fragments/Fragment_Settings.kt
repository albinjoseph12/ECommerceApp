package com.example.sylhetjerseyhouse.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sylhetjerseyhouse.Activities.Login
import com.example.sylhetjerseyhouse.R

class Fragment_Settings : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val sharedPrefs by lazy {
        requireActivity().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.id_settingsToolbar)

        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar.title = "Settings"

        val logout: ConstraintLayout = view.findViewById(R.id.id_LogOutBtn)
        logout.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(requireContext(), Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        return view
    }
}
