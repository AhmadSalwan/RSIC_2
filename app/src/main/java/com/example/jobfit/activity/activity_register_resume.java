package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.jobfit.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(activity_register_resume.this, MainActivity.class);
                startActivity(intent); // Mulai activity Homepage
            }
        });
    }
}