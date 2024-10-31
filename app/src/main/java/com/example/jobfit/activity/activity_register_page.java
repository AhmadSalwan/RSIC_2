package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
            if (TextUtils.isEmpty(uName) || uName.length() > 20) {
                binding.nameInputLayout.setError("Name is required and should not exceed 20 characters");
                return;
            }

            if (TextUtils.isEmpty(uEmail) || !uEmail.endsWith("@gmail.com")) {
                binding.emailInputLayout.setError("Email is required and must end with @gmail.com");
                return;
            }

            if (TextUtils.isEmpty(uPhoneNumber) || uPhoneNumber.length() > 14) {
                binding.phoneEditText.setError("Phone number is required and must not exceed 14 characters");
                return;
            }

            if (TextUtils.isEmpty(selectedGender)) {
                binding.genderDropdown.setError("Please select your gender");
                return;
            }

            // Check if email already exists
            if (dbHelper.isEmailExists(uEmail)) {
                binding.emailInputLayout.setError("This email is already registered");
                Toast.makeText(activity_register_page.this, "Email already used, please use another one.", Toast.LENGTH_SHORT).show();
                return;
            }

            // If all inputs are valid, proceed to activity_register_image
            Intent intent = new Intent(activity_register_page.this, activity_register_resume.class);
            intent.putExtra("name", uName);
            intent.putExtra("email", uEmail);
            intent.putExtra("phone", uPhoneNumber);
            intent.putExtra("gender", selectedGender);
            startActivity(intent);
        });
    }
}
