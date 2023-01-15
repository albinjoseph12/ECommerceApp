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
import com.example.sylhetjerseyhouse.R

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






    private fun registerUser(newEmail: String, newPassword: String) {

        val request: StringRequest =
            object : StringRequest(Method.POST, url, Response.Listener { response: String ->
                emailSignUp.setText("")
                passwordSignUp.setText("")
                confirmPasswordSignUp.setText("")
                Toast.makeText(this@SignUp, response, Toast.LENGTH_SHORT).show()

            }, Response.ErrorListener { error: VolleyError ->
                Toast.makeText(this@SignUp, error.message, Toast.LENGTH_SHORT).show()
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params: MutableMap<String, String> = HashMap()
                    params["email"] = newEmail
                    params["password"] = newPassword

                    return params
                }
            }
        val requestQueue : RequestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(request)


    }











}