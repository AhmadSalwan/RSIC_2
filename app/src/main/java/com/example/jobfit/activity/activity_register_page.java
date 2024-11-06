package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jobfit.R;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.databinding.ActivityRegisterPageBinding;

public class activity_register_page extends AppCompatActivity {
    private ActivityRegisterPageBinding binding;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelper(this);

        // Set up gender dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_dropdown_item);
        binding.genderDropdown.setAdapter(adapter);

        // Button click listener
        binding.continueRegist.setOnClickListener(v -> {
            String uName = binding.nameInputLayout.getText().toString().trim();
            String uEmail = binding.emailInputLayout.getText().toString().trim();
            String uPhoneNumber = binding.phoneEditText.getText().toString().trim();
            String selectedGender = binding.genderDropdown.getText().toString().trim();

            // Input validation
            if (TextUtils.isEmpty(uName)) {
                binding.nameInputLayout.setError("Nama tidak boleh kosong");
                return;
            } else if (uName.length() > 20) {
                binding.nameInputLayout.setError("Nama tidak boleh melebihi 20 karakter");
                return;
            } else {
                binding.nameInputLayout.setError(null);
            }

            if (TextUtils.isEmpty(uEmail)) {
                binding.emailInputLayout.setError("Email tidak boleh kosong");
                return;
            } else if (!uEmail.endsWith("@gmail.com")) {
                binding.emailInputLayout.setError("Email harus diakhiri dengan @gmail.com");
                return;
            } else {
                binding.emailInputLayout.setError(null);
            }

            if (TextUtils.isEmpty(uPhoneNumber)) {
                binding.phoneEditText.setError("Nomor telepon tidak boleh kosong");
                return;
            } else if (!uPhoneNumber.matches("\\d+")) {
                binding.phoneEditText.setError("Nomor telepon hanya boleh berisi angka");
                return;
            } else if (uPhoneNumber.length() > 14) {
                binding.phoneEditText.setError("Nomor telepon tidak boleh melebihi 14 karakter");
                return;
            } else {
                binding.phoneEditText.setError(null);
            }

            if (TextUtils.isEmpty(selectedGender)) {
                binding.genderDropdown.setError("Silakan pilih jenis kelamin Anda");
                return;
            } else {
                binding.genderDropdown.setError(null);
            }

            // Check if email already exists
            if (dbHelper.isEmailExists(uEmail)) {
                binding.emailInputLayout.setError("Email ini sudah terdaftar");
                Toast.makeText(activity_register_page.this, "Email sudah digunakan, silakan gunakan yang lain.", Toast.LENGTH_SHORT).show();
                return;
            }

            // If all inputs are valid, proceed to activity_register_resume
            Intent intent = new Intent(activity_register_page.this, activity_register_resume.class);
            intent.putExtra("name", uName);
            intent.putExtra("email", uEmail);
            intent.putExtra("phone", uPhoneNumber);
            intent.putExtra("gender", selectedGender);
            startActivity(intent);
        });
    }
}
