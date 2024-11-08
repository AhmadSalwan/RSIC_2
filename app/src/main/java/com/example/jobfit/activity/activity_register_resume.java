package com.example.jobfit.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfit.R;

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

            // Simpan data ke SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", uEmail); // Simpan email
            editor.putString("skills", uSkills); // Simpan skills
            editor.putString("experiences", uExperiences); // Simpan pengalaman
            editor.apply(); // Simpan perubahan

            // Lanjutkan ke activity selanjutnya
            Intent intent = new Intent(activity_register_resume.this, activity_register_image.class);
            intent.putExtra("name", uName);
            intent.putExtra("email", uEmail);
            intent.putExtra("phone", uPhoneNumber);
            intent.putExtra("gender", uGender);
            intent.putExtra("skills", uSkills);
            intent.putExtra("experiences", uExperiences);
            intent.putExtra("achievements", uAchievements);
            intent.putExtra("education", uEducation);
            startActivity(intent);
            finish();
        });
    }
}
