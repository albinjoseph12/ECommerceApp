package com.eshopthis.finds.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eshopthis.finds.API.ApiController
import com.eshopthis.finds.R
import com.eshopthis.finds.models.User
import com.google.gson.Gson
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
            startActivity(Intent(this, SignUp::class.java))
        }

        btnLogin.setOnClickListener {
            val lEmail = logEmail.text.toString().trim()
            val lPassword = logPassword.text.toString().trim()

            if (lEmail.isEmpty() || !emailValidation(lEmail)) {
                logEmail.error = "Invalid Email Address"
            } else {
                loginUser(lEmail, lPassword)
            }
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).also {
            finish()
        })
    }

    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun loginUser(email: String, password: String) {
        val user = User(username = email, password = password)

        val handler = CoroutineExceptionHandler { _, throwable ->
            Toast.makeText(this@Login, "Exception: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
            Log.e("Login", "Coroutine Exception: ${throwable.localizedMessage}")
        }

        CoroutineScope(Dispatchers.Main).launch(handler) {
            try {
                // Log the Android request payload
                val gson = Gson()
                val requestBody = gson.toJson(user)
                Log.d("Login", "Android Request Payload: $requestBody")

                val response = ApiController.apiService.loginUser(user)
                Log.d("Login", "Request URL: ${response.raw().request.url}")
                Log.d("Login", "Response Code: ${response.code()}")

                if (response.isSuccessful) {
                    val loggedInUser = response.body()
                    if (loggedInUser != null) {
                        sharedPrefs.edit().apply {
                            putBoolean("isLoggedIn", true)
                            apply()
                        }
                        navigateToMainActivity()
                    } else {
                        Toast.makeText(this@Login, "Login Failed: Invalid response", Toast.LENGTH_SHORT).show()
                        Log.e("Login", "API Error: Invalid response")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = "HTTP Error: ${response.code()} - $errorBody"
                    Toast.makeText(this@Login, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("Login", errorMessage)
                }
            } catch (e: Exception) {
                Toast.makeText(this@Login, "Exception: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                Log.e("Login", "Login Exception: ${e.localizedMessage}", e)
            }
        }
    }
}