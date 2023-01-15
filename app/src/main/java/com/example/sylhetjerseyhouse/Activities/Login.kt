package com.example.sylhetjerseyhouse.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.example.sylhetjerseyhouse.API.ApiController
import com.example.sylhetjerseyhouse.API.ApiSet
import com.example.sylhetjerseyhouse.API.Data
import com.example.sylhetjerseyhouse.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

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

            if (emailValidation(lEmail)) {
                checkEmailPassword(lEmail, lPassword)
            } else {
                logEmail.error="Invalid Email Address"
            }
        }


    }



    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkEmailPassword(email: String, password: String):Boolean{
        // var qry : String = "?email="+email+"&password="+password

        ApiController.apiInterface.loginUser(email, password).enqueue(object : Callback<Data>)


        return false;
    }


}