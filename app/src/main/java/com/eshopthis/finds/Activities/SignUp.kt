package com.eshopthis.finds.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eshopthis.finds.API.ApiController
import com.eshopthis.finds.R
import com.eshopthis.finds.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {

    private lateinit var emailSignUp: EditText
    private lateinit var passwordSignUp: EditText
    private lateinit var confirmPasswordSignUp: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var referralEmailEditText: EditText
    private lateinit var signUpBtn: Button
    private lateinit var gotoLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailSignUp = findViewById(R.id.SignUpEmailID)
        passwordSignUp = findViewById(R.id.newPasswordSignUpId)
        confirmPasswordSignUp = findViewById(R.id.confirmNewPasswordSignUpId)
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        referralEmailEditText = findViewById(R.id.referralEmailEditText)
        signUpBtn = findViewById(R.id.signupButtonID)
        gotoLogin = findViewById(R.id.goToLoginPageID)

        gotoLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        signUpBtn.setOnClickListener {
            val newEmail = emailSignUp.text.toString().trim()
            val newPassword = passwordSignUp.text.toString().trim()
            val confirmNewPassword = confirmPasswordSignUp.text.toString().trim()

            if (!emailValidation(newEmail)) {
                emailSignUp.error = "Invalid Email Address"
            } else if (newPassword != confirmNewPassword) {
                passwordSignUp.error = "Passwords do not match"
                confirmPasswordSignUp.error = "Passwords do not match"
            } else if (newPassword.length < 8) {
                passwordSignUp.error = "Password must be at least 8 characters"
                confirmPasswordSignUp.error = "Password must be at least 8 characters"
            } else {
                checkUsernameAvailability(newEmail, newPassword)
            }
        }
    }

    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkUsernameAvailability(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val checkUsernameResponse = ApiController.apiService.checkUsername(email)
                if (checkUsernameResponse.isSuccessful && checkUsernameResponse.body()?.success == true) {
                    registerUser(email, password)
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SignUp, "Username already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@SignUp, "Network error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        val user = User(
            username = email,
            password = password,
            firstName = firstNameEditText.text.toString(),
            lastName = lastNameEditText.text.toString(),
            referralEmail = referralEmailEditText.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val registerResponse = ApiController.apiService.registerUser(user)
                runOnUiThread {
                    if (registerResponse.isSuccessful && registerResponse.body()?.success == true) {
                        Toast.makeText(this@SignUp, "Registration successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUp, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignUp, "Registration failed: ${registerResponse.body()?.message}", Toast.LENGTH_SHORT).show()
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
