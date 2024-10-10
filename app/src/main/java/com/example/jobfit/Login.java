package com.example.jobfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    DBHelper dbHelper;
    EditText name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);

        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.login);
        Button regist = findViewById(R.id.regist);
        name = findViewById(R.id.nameInputLayout);
        email = findViewById(R.id.emailInputLayout);


        login.setOnClickListener(v -> {
            String username = name.getText().toString().trim();
            String useremail = email.getText().toString().trim();

            if (username.isEmpty() || useremail.isEmpty()) {
                Toast.makeText(Login.this, "Username dan email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dbHelper.isUserValid(useremail, username)) {
                // Intent untuk memulai MainActivity
                Intent intent = new Intent(Login.this, MainActivity.class);
                if (dbHelper.isUserValid(useremail, username)) {
                    // Simpan email di SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", useremail); // Simpan email
                    editor.apply(); // Simpan perubahan

                    // Intent untuk memulai MainActivity
                    Intent loginintent = new Intent(Login.this, MainActivity.class);
                    startActivity(loginintent);
                    finish(); // Menghapus activity login dari stack
                }
            } else {
                Toast.makeText(Login.this, "Username atau email tidak valid", Toast.LENGTH_SHORT).show();
            }
        });
        regist.setOnClickListener(v -> {
            // Intent to start MainActivity
            Intent intent = new Intent(Login.this, activity_register_page.class);
            startActivity(intent);
        });
    }

}