package com.ad.mediax.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ad.mediax.R;
import com.google.android.exoplayer2.util.Log;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    sharedPref=getApplicationContext().getSharedPreferences("user",0);
                    String Username = sharedPref.getString("username", "");
                    String Email = sharedPref.getString("email", "");
                    String Password = sharedPref.getString("password", "");

                    if (Username.equals("") && Email.equals("") && Password.equals("")) {
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    Log.d("df", e.getMessage());
                }

            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}
