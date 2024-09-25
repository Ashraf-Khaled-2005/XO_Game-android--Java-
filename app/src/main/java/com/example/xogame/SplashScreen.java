package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After 2 seconds, start the main activity
                Intent intent = new Intent(getBaseContext(), StartingPage.class);
                startActivity(intent);
                finish(); // Close the SplashActivity
            }
        }, 2000);
    }
}