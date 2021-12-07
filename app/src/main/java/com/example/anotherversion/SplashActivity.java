package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private String password;
    private static int SPLASH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        password = pref.getString("password", "");
        Handler handler = new Handler();
        handler.postDelayed (new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (password.equals("")){
                    intent = new Intent(getApplicationContext(), Introduction.class);
                }
                else {
                    intent = new Intent(getApplicationContext(), EnterPassword.class);
                }
                startActivity(intent);
                finish();

            }
        },SPLASH);

    }
}