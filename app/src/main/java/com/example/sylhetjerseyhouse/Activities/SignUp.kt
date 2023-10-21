package com.example.sylhetjerseyhouse.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sylhetjerseyhouse.API.ApiController
import com.example.sylhetjerseyhouse.DataClass.PostData
import com.example.sylhetjerseyhouse.MainActivity
import com.example.sylhetjerseyhouse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUp : AppCompatActivity() {

    private lateinit var emailSignUp: EditText
    private lateinit var passwordSignUp: EditText
    private lateinit var confirmPasswordSignUp: EditText
    private lateinit var signUpBtn: Button
    private lateinit var gotoLogin: TextView
    private val url: String = "https://185.27.134.11/api/";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailSignUp = findViewById(R.id.SignUpEmailID)
        passwordSignUp = findViewById(R.id.newPasswordSignUpId)
        confirmPasswordSignUp = findViewById(R.id.confirmNewPasswordSignUpId)
        signUpBtn = findViewById(R.id.signupButtonID)
        gotoLogin = findViewById(R.id.goToLoginPageID)


        gotoLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


        signUpBtn.setOnClickListener {
            val newEmail = emailSignUp.text.toString().trim()
            val newPassword = passwordSignUp.text.toString().trim()
            val confirmNewPassword = confirmPasswordSignUp.text.toString().trim()

            if (!emailValidation(newEmail)) {
                emailSignUp.error = "Invalid Email Address"
            } else if (newPassword != confirmNewPassword) {
                passwordSignUp.error = "Password must be same"
                confirmPasswordSignUp.error = "Password must be same"
            } else if (newPassword.length < 8) {
                passwordSignUp.error = "no less than 8 digit"
                confirmPasswordSignUp.error = "no less than 8 digit"
            } else {
                registerUser(newEmail, newPassword);
            }
        }


    }







    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



    fun registerUser(newEmail: String, newPassword: String) {

        val postData = PostData(newEmail, newPassword)
        // Use CoroutineScope to perform the API call asynchronously
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiController.apiService.registerUser(postData)

                if (response.isSuccessful) {
                    val apiResponse = response.body()

                    runOnUiThread {
                        if (apiResponse != null) {
                            if (apiResponse.message == "Successful") {
                                // Registration successful, update UI accordingly
                                Toast.makeText(this@SignUp, "Registration successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignUp, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                // Registration failed, show an error message
                                Toast.makeText(this@SignUp, ""+apiResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    // Handle other HTTP status codes if needed
                    runOnUiThread {
                        Toast.makeText(this@SignUp, "HTTP Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@SignUp, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }



}