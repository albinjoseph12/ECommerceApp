package com.eshopthis.finds.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.eshopthis.finds.R

class SplashScreen : AppCompatActivity() {

    private lateinit var logoImage: ImageView
    private lateinit var appName: TextView
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logoImage = findViewById(R.id.logoImageView)
        appName = findViewById(R.id.apNameTxtView)
        lottieAnimationView = findViewById(R.id.lottieSplashScreen)

        logoImage.animate().translationY(-1670f).setDuration(400).setStartDelay(2000)
        appName.animate().translationY(-1670f).setDuration(400).setStartDelay(2000)
        lottieAnimationView.animate().translationY(1700f).setDuration(400).setStartDelay(2000)

        Handler().postDelayed({
                val i = Intent(applicationContext, Login::class.java)
        startActivity(i)
        }, 2460)
    }
}