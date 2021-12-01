package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The type Splash activity.
 */
public class SplashActivity extends AppCompatActivity {
    private String password;
    private static int SPLASH = 1000;
    private ImageView logoIm;
    /**
     * The Animation.
     */
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoIm = findViewById(R.id.logoIm);

        SharedPreferences pref = getSharedPreferences("prefs", 0);
        password = pref.getString("password", "");
        //animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        //logoIm.setAnimation(animation);
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