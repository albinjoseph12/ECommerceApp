package com.example.sylhetjerseyhouse.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sylhetjerseyhouse.MainActivity;
import com.example.sylhetjerseyhouse.R;

public class SplashScreen extends AppCompatActivity {

    ImageView logoImage;
    TextView appName;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);





        logoImage = findViewById(R.id.logoImageView);
        appName = findViewById(R.id.apNameTxtView);
        lottieAnimationView = findViewById(R.id.lottieSplashScreen);

        logoImage.animate().translationY(-1670).setDuration(400).setStartDelay(2000);
        appName.animate().translationY(-1670).setDuration(400).setStartDelay(2000);
        lottieAnimationView.animate().translationY(1700).setDuration(400).setStartDelay(2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        },2460);

    }
}