package com.example.jobfit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobfit.databinding.ActivityRegisterResumeBinding;

public class activity_register_resume extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_resume);

        // Inisialisasi tombol
        Button continueButton = findViewById(R.id.continue_regist);

        // Set onClickListener untuk tombol Continue
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk berpindah ke activity Homepage
                Intent intent = new Intent(activity_register_resume.this, activity_register_page.class);
                startActivity(intent); // Mulai activity Homepage
            }
        });
    }
}