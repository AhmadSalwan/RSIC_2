package com.example.jobfit.fragment;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobfit.EditResumeFragment;
import com.example.jobfit.R;
import com.example.jobfit.db.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileFragment extends Fragment {
    private DBHelper dbHelper; // DBHelper untuk akses database
    private EditText editTextName, editTextPhone, editTextEmail;
    AutoCompleteTextView editGender;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AutoCompleteTextView genderDropdown = view.findViewById(R.id.genderDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_options, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDropdown.setAdapter(adapter);
        dbHelper = new DBHelper(getContext());
        editTextName = view.findViewById(R.id.nameInputLayout);
        editTextEmail = view.findViewById(R.id.emailInputLayout);
        editTextPhone = view.findViewById(R.id.phoneEditText);
        editGender = view.findViewById(R.id.genderDropdown);

        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);
        if (email != null) {
            // Ambil data pengguna dari database
            dbHelper.getUserData(email);
        }
        // Find the button by its ID
        Button editButton = view.findViewById(R.id.continue_editbtn);

        // Set an OnClickListener to handle button clicks
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the fragment you want to switch to
                EditResumeFragment editResumeFragment = new EditResumeFragment();

                // Get the FragmentManager and start a transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment
                fragmentTransaction.replace(R.id.main, editResumeFragment);

                // Optionally, add this transaction to the back stack so the user can navigate back
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
        Button saveButton = view.findViewById(R.id.save_data);
        saveButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String phone = editTextPhone.getText().toString();
            String gender = editGender.getText().toString();
            String newemail = editTextEmail.getText().toString();

            if (dbHelper.updateUserProfile(email, name, phone, gender,newemail)) {
                Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to update profile!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
