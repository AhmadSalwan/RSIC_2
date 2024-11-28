package com.example.jobfit.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jobfit.API.Api;
import com.example.jobfit.API.Job;
import com.example.jobfit.API.UserInput;
import com.example.jobfit.R;
import com.example.jobfit.API.RetrofitClient;
import com.example.jobfit.activity.ResultActivity;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.db.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompassFragment extends Fragment {
    private TextView textView;
    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        dbHelper = new DBHelper(getContext());

        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);

        String jobRole = getActivity().getSharedPreferences("CompassPrefs", getContext().MODE_PRIVATE)
                .getString("job_role", null);
        String company = getActivity().getSharedPreferences("CompassPrefs", getContext().MODE_PRIVATE)
                .getString("company", null);

        // Log untuk debugging
        Log.d("CompassFragment", "Retrieved jobRole: " + jobRole);
        Log.d("CompassFragment", "Retrieved company: " + company);

        // Find the TextView to display the data
        textView = view.findViewById(R.id.cardDetailsText);

        // Display the cardData in the TextView
        if (jobRole != null && company != null) {
            textView.setText(jobRole + " at " + company);
        } else {
            textView.setText("No data received");
        }

        // Find the "Check the Result" button
        Button checkResultButton = view.findViewById(R.id.checkResultButton);

        // Set up click listener for the button
        checkResultButton.setOnClickListener(v -> {
            User user = dbHelper.getUser(email);
            if (user != null) {
                String skills = user.getSkills();
                int experience = Integer.parseInt(user.getExperience());
                UserInput userInput = new UserInput(skills, experience, company, jobRole);

                // Call API with UserInput
                matchJob(userInput);
            } else {
                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void matchJob(UserInput userInput) {
        Api apiService = RetrofitClient.getClient().create(Api.class);
        Call<List<Job>> call = apiService.matchJob(userInput);

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> jobs = response.body();
                    if (!jobs.isEmpty()) {
                        Job job = jobs.get(0); // Ambil hasil pertama
                        Intent intent = new Intent(getContext(), ResultActivity.class);
                        intent.putExtra("company", job.getCompany());
                        intent.putExtra("jobRole", job.getJob_Role());
                        intent.putExtra("matchPercentage", job.getMatch_Percentage());
                        intent.putExtra("skills", userInput.getSkills());
                        intent.putExtra("experience", userInput.getExperience());
                        startActivity(intent);
                    } else {
                        textView.setText("No matches found");
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to get match results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Toast.makeText(getContext(), "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
