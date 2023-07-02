package com.example.sylhetjerseyhouse.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sylhetjerseyhouse.API.ApiController
import com.example.sylhetjerseyhouse.API.ApiResponse
import com.example.sylhetjerseyhouse.API.ApiSet
import com.example.sylhetjerseyhouse.API.RequestData
import com.example.sylhetjerseyhouse.R
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class SignUp : AppCompatActivity() {

    private lateinit var emailSignUp: EditText
    private lateinit var passwordSignUp: EditText
    private lateinit var confirmPasswordSignUp: EditText
    private lateinit var signUpBtn: Button
    private lateinit var gotoLogin: TextView
    private val url: String = "https://food-buzz.000webhostapp.com/signup_user.php";

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
            } else if (newPassword.length < 8 || confirmNewPassword.length < 8) {
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

        val requestData = RequestData(newEmail, newPassword)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            try {
                val response = ApiController.apiInterface.postData(requestData)
                handleApiResponse(response)
            } catch (e: Exception) {
                // Handle error response here
                val errorMessage = "Error: ${e.message}"
                // Handle the error message here (e.g., show a toast or update UI)
                Toast.makeText(this@SignUp, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun handleApiResponse(apiResponse: ApiResponse) {
        // Handle successful response here
        val message = apiResponse.message
        // Handle the response message here (e.g., show a toast or update UI)
        Toast.makeText(this@SignUp, message, Toast.LENGTH_SHORT).show()
    }










}

private fun CoroutineScope.handleApiResponse(apiResponse: Call<ApiResponse>) {

}
