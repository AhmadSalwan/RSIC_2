package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;


import com.example.jobfit.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;

public class intro_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler(Looper.getMainLooper() ).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(intro_activity.this, activity_register_page.class));
                finish();
            }
        },1000);
    }
}