package com.example.jobfit.fragment;

import android.os.Bundle;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompassFragment extends Fragment {

    private String cardData;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        // Retrieve the data from the arguments
        if (getArguments() != null) {
            cardData = getArguments().getString("card_data");
        }

        // Find the TextView to display the data
        textView = view.findViewById(R.id.cardDetailsText);

        // Display the cardData in the TextView
        if (cardData != null) {
            textView.setText(cardData);
        } else {
            textView.setText("No data received");
        }

        // Find the "Check the Result" button
        Button checkResultButton = view.findViewById(R.id.checkResultButton);

        // Set up click listener for the button
        checkResultButton.setOnClickListener(v -> {
            // Retrieve user input (skills and experience) and call API to match jobs
            retrieveUserDataAndMatchJob();
        });

        return view;
    }

    // Method to retrieve user data (skills and experience) and call API to match jobs
    private void retrieveUserDataAndMatchJob() {
        // Retrieve data from SharedPreferences
        String skills = getContext().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("skills", "");
        String experienceStr = getContext().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("experiences", "0");

        if (skills.isEmpty() || experienceStr.isEmpty()) {
            Toast.makeText(getContext(), "Skills or experience not available", Toast.LENGTH_SHORT).show();
            return;
        }

        int experience = Integer.parseInt(experienceStr); // Convert experience to int

        // Create UserInput object with the data retrieved
        UserInput userInput = new UserInput(skills, experience);

        // Call the job matching API
        top_matches(userInput);
    }

    // Method to call the API and match jobs based on user input (skills and experience)
    private void top_matches(UserInput userInput) {
        Api apiService = RetrofitClient.getClient().create(Api.class);
        Call<List<Job>> call = apiService.top_matches(userInput);

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> jobs = response.body();

                    // Display the job matching result in TextView
                    StringBuilder jobDetails = new StringBuilder();
                    for (Job job : jobs) {
                        jobDetails.append("Job Role: ").append(job.getJob_Role()).append("\n");
                        jobDetails.append("Company: ").append(job.getCompany()).append("\n");
                        jobDetails.append("Location: ").append(job.getLocation()).append("\n\n");
                    }

                    textView.setText(jobDetails.toString()); // Show job details in the TextView
                } else {
                    Toast.makeText(getContext(), "No jobs matched", Toast.LENGTH_SHORT).show();
                    textView.setText("No jobs matched");
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                // Handle failure
                Toast.makeText(getContext(), "Failed to match jobs: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
