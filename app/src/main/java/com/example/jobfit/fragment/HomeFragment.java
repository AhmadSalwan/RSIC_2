package com.example.jobfit.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jobfit.API.Api;
import com.example.jobfit.API.Job;
import com.example.jobfit.R;
import com.example.jobfit.adapter.SwipeAdapter;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.model.Item;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    ImageView imageViewUser;
    private DBHelper dbHelper;
    private ArrayList<String> dataList;
    private ArrayList<Integer> imageList;
    private SwipeAdapter swipeAdapter;
    private Api apiService;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageViewUser = toolbar.findViewById(R.id.head_image);
        progressBar = view.findViewById(R.id.progressBar); // Initialize ProgressBar

        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);
        dbHelper = new DBHelper(getContext());

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobfit-api-0ea5f7774884.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(Api.class);

        dataList = new ArrayList<>();
        imageList = new ArrayList<>();
        swipeAdapter = new SwipeAdapter(getActivity(), dataList, imageList);

        if (email != null) {
            Bitmap profilePicture = dbHelper.getUserProfilePicture(email);
            if (profilePicture != null) {
                imageViewUser.setImageBitmap(profilePicture);
            } else {
                imageViewUser.setImageResource(R.drawable.main_image);
            }
        }

        SwipeFlingAdapterView swipeView = view.findViewById(R.id.swipe_view);
        swipeView.setAdapter(swipeAdapter);
        swipeView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                dataList.remove(0);
                imageList.remove(0);
                swipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {}

            @Override
            public void onRightCardExit(Object dataObject) {
                String cardData = (String) dataObject;
                addCardToCompassFragment(cardData);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {}

            @Override
            public void onScroll(float scrollProgressPercent) {}
        });

        fetchJobsData();

        return view;
    }

    // Fetch job data from API
    private void fetchJobsData() {
        progressBar.setVisibility(View.VISIBLE); // Show ProgressBar

        apiService.getCompaniesJobs().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                progressBar.setVisibility(View.GONE); // Hide ProgressBar

                if (response.isSuccessful() && response.body() != null) {
                    for (Job job : response.body()) {
                        dataList.add(job.getJob_Role() + " at " + job.getCompany());
                        imageList.add(R.drawable.gedung8); // Replace with an appropriate image resource
                    }
                    swipeAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to load jobs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide ProgressBar
                Toast.makeText(getContext(), "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addCardToCompassFragment(String cardData) {
        CompassFragment compassFragment = new CompassFragment();
        Bundle bundle = new Bundle();
        bundle.putString("card_data", cardData);
        compassFragment.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, compassFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

