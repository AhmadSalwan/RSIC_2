package com.example.jobfit;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobfit.databinding.ActivityRegisterResumeBinding;

public class activity_register_resume extends AppCompatActivity {
    EditText skill, exp, achv, edu;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_resume);
        skill = findViewById(R.id.skillEditText);
        exp = findViewById(R.id.experienceEditText);
        achv = findViewById(R.id.achievementEditText);
        edu = findViewById(R.id.educationEditText);

        continueButton = findViewById(R.id.continue_regist);
        continueButton.setOnClickListener(v -> {
            Intent prevIntent = getIntent();
            String uName = prevIntent.getStringExtra("name");
            String uEmail = prevIntent.getStringExtra("email");
            String uPhoneNumber = prevIntent.getStringExtra("phone");
            String uGender = prevIntent.getStringExtra("gender");

            // Ambil data resume dari form
            String uSkills = skill.getText().toString();
            String uExperiences = exp.getText().toString();
            String uAchievements = achv.getText().toString();
            String uEducation = edu.getText().toString();
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", uEmail); // Simpan email
            editor.apply(); // Simpan perubahan
            Intent intent = new Intent(activity_register_resume.this, activity_register_image.class);

            // Masukkan data resume ke intent
            intent.putExtra("name", uName);
            intent.putExtra("email", uEmail);
            intent.putExtra("phone", uPhoneNumber);
            intent.putExtra("gender", uGender);
            intent.putExtra("skills", uSkills);
            intent.putExtra("experiences", uExperiences);
            intent.putExtra("achievements", uAchievements);
            intent.putExtra("education", uEducation);
            // Pergi ke halaman selanjutnya
            startActivity(intent); // Mulai activity Homepage
            finish();

        });
    }

}