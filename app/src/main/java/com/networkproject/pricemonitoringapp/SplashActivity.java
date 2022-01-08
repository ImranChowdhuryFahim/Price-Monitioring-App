package com.networkproject.pricemonitoringapp;

import static com.networkproject.pricemonitoringapp.LoginActivity.ACCOUNT_TYPE;
import static com.networkproject.pricemonitoringapp.LoginActivity.IS_LOGGED_IN;
import static com.networkproject.pricemonitoringapp.LoginActivity.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private Boolean isLoggedIn;
    private String accountType;
    private String infoAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);

        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean(IS_LOGGED_IN,false);



        new CountDownTimer(2000, 1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(isLoggedIn)
                {

                        Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();

                }

                else {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }.start();
    }
}