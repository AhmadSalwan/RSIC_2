package com.example.jobfit.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobfit.R;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.fragment.ProfileFragment;
import com.google.android.material.textfield.TextInputEditText;

public class EditResumeFragment extends Fragment {
    private EditText editTextSkills, editTextExperiences, editTextAchievements, editTextEducation;
    private DBHelper dbHelper;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_resume, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new DBHelper(getContext());

        // Find the EditText fields
        editTextSkills = view.findViewById(R.id.skillEditText);
        editTextExperiences = view.findViewById(R.id.experienceEditText);
        editTextAchievements = view.findViewById(R.id.achievementEditText);
        editTextEducation = view.findViewById(R.id.educationEditText);
        // Find the save button
        Button saveButton = view.findViewById(R.id.save_resume);

        email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);
        // Set an OnClickListener to handle button clicks
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save data to database
                String skills = editTextSkills.getText().toString();
                String experiences = editTextExperiences.getText().toString();
                String achievements = editTextAchievements.getText().toString();
                String education = editTextEducation.getText().toString();

                boolean updated = dbHelper.updateUserData(email, skills, experiences, achievements, education);

                if (updated) {
                    Toast.makeText(getContext(),
                            "Skills: " + skills + "\nExperience: " + experiences,
                            Toast.LENGTH_LONG).show();
                    // Navigate back to ProfileFragment
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main, profileFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getContext(), "Failed to update data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
