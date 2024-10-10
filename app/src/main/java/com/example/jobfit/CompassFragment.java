package com.example.jobfit;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CompassFragment extends Fragment {
    private DBHelper dbHelper;
    private ImageView imageViewUser;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        dbHelper = new DBHelper(getContext());
        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data list
        itemList = new ArrayList<>();
        itemList.add(new Item("Title 1", "Description 1"));
        itemList.add(new Item("Title 2", "Description 2"));
        itemList.add(new Item("Title 3", "Description 3"));
        // Add more items...

        // Set adapter
        adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageViewUser = toolbar.findViewById(R.id.head_image);
        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);

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



        return view;
    }
}
