package com.example.sylhetjerseyhouse.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sylhetjerseyhouse.API.ApiController
import com.example.sylhetjerseyhouse.API.PostData
import com.example.sylhetjerseyhouse.MainActivity
import com.example.sylhetjerseyhouse.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {

    private lateinit var logEmail: EditText
    private lateinit var logPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var goToSignUp: TextView
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPrefs.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            navigateToMainActivity()
        }

        logEmail = findViewById(R.id.email_loginID)
        logPassword = findViewById(R.id.password_loginID)
        btnLogin = findViewById(R.id.loginButtonID)
        goToSignUp = findViewById(R.id.goToSignUpPageID)

        goToSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val lEmail = logEmail.text.toString().trim()
            val lPassword = logPassword.text.toString().trim()

            if (lEmail.isEmpty() || !emailValidation(lEmail)) {
                logEmail.error = "Invalid Email Address"
            } else {
                checkEmailPassword(lEmail, lPassword)
            }
        }
    }


    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Exit App")
        alertDialog.setMessage("Do you want to exit this application?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        alertDialog.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkEmailPassword(email: String, password: String) {
        val postData = PostData(email, password)

        val handler = CoroutineExceptionHandler { _, throwable ->
            runOnUiThread {
                Toast.makeText(this@Login, "Exception: ${throwable.message}", Toast.LENGTH_SHORT).show()
            }
        }

        CoroutineScope(Dispatchers.IO).launch(handler) {
            try {
                val response = ApiController.apiService.loginUser(postData)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    runOnUiThread {
                        if (apiResponse != null) {
                            if (apiResponse.message == "Login successful") {
                                val editor = sharedPrefs.edit()
                                editor.putBoolean("isLoggedIn", true)
                                editor.apply()
                                navigateToMainActivity()
                            } else {
                                Toast.makeText(this@Login, apiResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@Login, "HTTP Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@Login, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
