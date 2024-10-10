package com.example.jobfit;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobfit.databinding.ActivityIntroBinding;

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