package com.example.jobfit.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jobfit.R;
import com.example.jobfit.adapter.SwipeAdapter;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.model.Item;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageView imageViewUser;
    private DBHelper dbHelper;
    private ArrayList<String> dataList;
    private ArrayList<Integer> imageList;  // List to store images
    private SwipeAdapter swipeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageViewUser = toolbar.findViewById(R.id.head_image);
        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);
        dbHelper = new DBHelper(getContext());

        // Initialize data
        dataList = new ArrayList<>();
        dataList.add("Card 1");
        dataList.add("Card 2");
        dataList.add("Card 3");
        dataList.add("Card 4");

        // Initialize image list (use your actual image resource IDs here)
        imageList = new ArrayList<>();
        imageList.add(R.drawable.gedung5);  // Replace with actual drawable resource
        imageList.add(R.drawable.gedung6);
        imageList.add(R.drawable.gedung7);
        imageList.add(R.drawable.gedung8);

        swipeAdapter = new SwipeAdapter(getActivity(), dataList, imageList);


        if (email != null) {
            // Mendapatkan gambar profil
            Bitmap profilePicture = dbHelper.getUserProfilePicture(email);
            if (profilePicture != null) {
                imageViewUser.setImageBitmap(profilePicture); // Set gambar pada ImageView
            } else {
                // Set gambar default jika tidak ada
                imageViewUser.setImageResource(R.drawable.main_image);
            }
        }


        // Set up the SwipeFlingAdapterView
        SwipeFlingAdapterView swipeView = view.findViewById(R.id.swipe_view);
        swipeView.setAdapter(swipeAdapter);
        swipeView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                dataList.remove(0);
                imageList.remove(0);  // Also remove the image from the list
                swipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                // Handle left swipe
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // Handle right swipe
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Called when adapter is about to be empty
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                // Handle scroll effect
            }
        });

        return view;
    }
}
