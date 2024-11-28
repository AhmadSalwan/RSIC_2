package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobfit.R;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi elemen UI
        TextView matchingPercentageTextView = findViewById(R.id.matchingPercentage);
        TextView matchTextView = findViewById(R.id.matchText);
        TextView jobTitleTextView = findViewById(R.id.jobTitle);
        TextView companyNameTextView = findViewById(R.id.companyName);

        // Ambil data dari Intent
        Intent intent = getIntent();
        String company = intent.getStringExtra("company");
        String jobRole = intent.getStringExtra("jobRole");
        double matchPercentage = intent.getDoubleExtra("matchPercentage", 0.0);
        String skills = intent.getStringExtra("skills");
        int experience = intent.getIntExtra("experience", 0);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedPercentage = decimalFormat.format(matchPercentage);

        // Perbarui UI dengan data yang diterima
        matchingPercentageTextView.setText(formattedPercentage + "%");
        matchTextView.setText("You're matched!");
        jobTitleTextView.setText(jobRole);
        companyNameTextView.setText(company);

    }
}