package com.example.sylhetjerseyhouse.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sylhetjerseyhouse.API.ApiController
import com.example.sylhetjerseyhouse.API.PostData
import com.example.sylhetjerseyhouse.API.RequestData
import com.example.sylhetjerseyhouse.MainActivity
import com.example.sylhetjerseyhouse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {

    private lateinit var logEmail: EditText
    private lateinit var logPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var goToSignUp : TextView
    private val url : String = "https://food-buzz.000webhostapp.com/login_user.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        logEmail = findViewById(R.id.email_loginID)
        logPassword = findViewById(R.id.password_loginID)
        btnLogin = findViewById(R.id.loginButtonID)
        goToSignUp = findViewById(R.id.goToSignUpPageID)

        goToSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {
            val lEmail = logEmail.text.toString().trim()
            val lPassword = logPassword.text.toString().trim()

            if (lEmail.isEmpty() && lPassword.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }else if (emailValidation(lEmail)) {
                var result : Boolean = checkEmailPassword(lEmail, lPassword)
                if (result == true){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                logEmail.error="Invalid Email Address"
            }
        }


    }



    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkEmailPassword(email: String, password: String):Boolean{
        val postData = PostData(email, password)
        var isLogged : Boolean = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiController.apiService.loginUser(postData)

                if (response.isSuccessful) {
                    val apiResponse = response.body()

                    runOnUiThread {
                        if (apiResponse != null) {
                            if (apiResponse.message == "Login successful") {
                                // Registration successful, update UI accordingly
                                Toast.makeText(this@Login, ""+apiResponse.message, Toast.LENGTH_SHORT).show()
                            } else {
                                // Registration failed, show an error message
                                Toast.makeText(this@Login, ""+apiResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    // Handle other HTTP status codes if needed
                    runOnUiThread {
                        Toast.makeText(this@Login, "HTTP Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                        isLogged = false
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@Login, "Exception: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return isLogged
    }


}