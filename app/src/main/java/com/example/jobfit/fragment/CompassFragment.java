package com.example.jobfit.fragment;

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
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.db.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompassFragment extends Fragment {
    private String cardData;
    private TextView textView;
    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        dbHelper = new DBHelper(getContext());

        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);

        // Retrieve the data from the arguments
        String jobRole;
        String company;
        if (getArguments() != null) {
            jobRole = getArguments().getString("job_role");
            company = getArguments().getString("company");
        } else {
            jobRole = null;
            company = null;
        }

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
                        Job job = jobs.get(0); // Assuming you want the first match
                        textView.setText("Match: " + job.getMatch_Percentage() + "%");

                        // Log the details
                        Log.d("LogData", "Company: " + job.getCompany());
                        Log.d("LogData", "Job Role: " + job.getJob_Role());
                        Log.d("LogData", "Match Percentage: " + job.getMatch_Percentage());
                        Log.d("LogData", "Skills: " + userInput.getSkills());
                        Log.d("LogData", "Experience: " + userInput.getExperience());
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
