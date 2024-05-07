package com.eshopthis.finds.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.eshopthis.finds.Activities.Login
import com.eshopthis.finds.Activities.ProfileDetailsActivity
import com.eshopthis.finds.R

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

        val profileDetailsBtn: ConstraintLayout = view.findViewById(R.id.constraintLayout7)
        profileDetailsBtn.setOnClickListener {
            val intent = Intent(requireContext(), ProfileDetailsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}