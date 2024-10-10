package com.example.jobfit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobfit.databinding.ActivityRegisterPageBinding;
import com.google.android.material.textfield.TextInputLayout;


public class activity_register_page extends AppCompatActivity {
    DBHelper dbHelper;

    Button button;
    EditText phone,name,email;
    AutoCompleteTextView gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);
        AutoCompleteTextView genderDropdown = findViewById(R.id.genderDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDropdown.setAdapter(adapter);

        dbHelper = new DBHelper(this);

        // Inisialisasi komponen input
        name = findViewById(R.id.nameInputLayout);
        email = findViewById(R.id.emailInputLayout);
        phone = findViewById(R.id.phoneEditText);
        gender = findViewById(R.id.genderDropdown);

        // Mengambil isi dari komponen

        button=findViewById(R.id.continue_regist);
        button.setOnClickListener( v ->{
            String uName = name.getText().toString();
            String uEmail = email.getText().toString();
            String uPhoneNumber = phone.getText().toString();
            String selectedGender = gender.getText().toString();
            // Validasi input
            if (TextUtils.isEmpty(uName)) {
                name.setError("Name is required");
                return;
            } else {
                name.setError(null); // Hilangkan error jika valid
            }

            if (TextUtils.isEmpty(uEmail)) {
                email.setError("Email is required");
                return;
            } else {
                email.setError(null);
            }

            if (TextUtils.isEmpty(uPhoneNumber)) {
                phone.setError("Phone number is required");
                return;
            }

            if (TextUtils.isEmpty(selectedGender)) {
                gender.setError("Please select your gender");
                return;
            }
            // Periksa apakah email sudah digunakan
            if (dbHelper.isEmailExists(uEmail)) {
                email.setError("This email is already registered");
                Toast.makeText(activity_register_page.this, "Email already used, please use another one.", Toast.LENGTH_SHORT).show();
                return; // Stop execution jika email sudah ada
            }


            // Jika semua input valid, lanjut ke activity_register_image
            Intent intent = new Intent(activity_register_page.this, activity_register_resume.class);
            intent.putExtra("name", uName);
            intent.putExtra("email", uEmail);
            intent.putExtra("phone", uPhoneNumber);
            intent.putExtra("gender", selectedGender);
            startActivity(intent);
        });
    }
}