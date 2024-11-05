package com.example.jobfit.fragment;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobfit.API.Api;
import com.example.jobfit.API.Job;
import com.example.jobfit.API.RetrofitClient;
import com.example.jobfit.API.UserInput;
import com.example.jobfit.R;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.db.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                // Panggil metode getUserData untuk mengambil data berdasarkan email
                Cursor userData = dbHelper.getUserData(email);

                // Pastikan data ada di Cursor
                if (userData != null && userData.moveToFirst()) {
                    String name = userData.getString(userData.getColumnIndex("username"));
                    String phone = userData.getString(userData.getColumnIndex("phone_number"));
                    String gender = userData.getString(userData.getColumnIndex("gender"));


                    // Set hint di setiap EditText
                    editTextName.setHint(name != null ? name : "Enter your name");
                    editTextEmail.setHint(email != null ? email : "Enter your email");
                    editTextPhone.setHint(phone != null ? phone : "Enter your phone number");
                    editGender.setHint(gender != null ? gender : "Enter your gender");
                }
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

            if (name.isEmpty()) {
                editTextName.setError("Name cannot be empty");
                editTextName.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                editTextPhone.setError("Phone number cannot be empty");
                editTextPhone.requestFocus();
                return;
            }

            if (gender.isEmpty()) {
                editGender.setError("Gender must be selected");
                editGender.requestFocus();
                return;
            }

            if (newemail.isEmpty()) {
                editTextEmail.setError("Email cannot be empty");
                editTextEmail.requestFocus();
                return;
            }

            if (dbHelper.updateUserProfile(email, name, phone, gender,newemail)) {
                Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to update profile!", Toast.LENGTH_SHORT).show();
            }
        });

        Button checkButton = view.findViewById(R.id.see_result);
        checkButton.setOnClickListener(v -> {
            User user=dbHelper.getUser(email);
            if (user != null) {
                // Buat objek UserInput dengan data skills dan experience
                String skills = user.getSkills(); // Ambil skills dari pengguna
                int experience = Integer.parseInt(user.getExperience()); // Ambil experience dan konversi ke int
                UserInput userInput = new UserInput(skills, experience);

                // Panggil API dengan UserInput
                matchJob(userInput);
            } else {
                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void matchJob(UserInput userInput) {
        Api apiService = RetrofitClient.getClient().create(Api.class);
        Call<List<Job>> call = apiService.matchJob(userInput);

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    List<Job> jobs = response.body();
                    for (Job job : jobs){
                        Log.d("Job", job.getJob_Role());
                    }
                }
                else if (response.isSuccessful()==false) {
                    Toast.makeText(getContext(), "response null", Toast.LENGTH_SHORT).show();
                }else if (response.body()!=null) {
                    Toast.makeText(getContext(), "response null", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getContext(), "Failed to match job", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("ProfileFragment", "Network request failed: " + t.getMessage());
                Toast.makeText(getContext(), "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
