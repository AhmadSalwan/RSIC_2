package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jobfit.R;

public class intro_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); // Pastikan layout untuk intro_activity sesuai

        // Menambahkan delay selama 2 detik (2000 ms) sebelum berpindah ke LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Pindah ke LoginActivity
                Intent intent = new Intent(intro_activity.this, Login.class);
                startActivity(intent);
                finish(); // Menghentikan IntroActivity agar tidak kembali lagi ketika menekan tombol back
            }
        }, 2000); // 2000 ms = 2 detik
    }
}
