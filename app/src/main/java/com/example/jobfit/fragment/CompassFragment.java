package com.example.jobfit.fragment;

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

import com.example.jobfit.adapter.ItemAdapter;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.R;
import com.example.jobfit.db.JobItemData;

public class CompassFragment extends Fragment {
    private DBHelper dbHelper;
    private ImageView imageViewUser;
    private RecyclerView rv_itemJob;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        dbHelper = new DBHelper(getContext());
        rv_itemJob = view.findViewById(R.id.rv_itemJobList);
        rv_itemJob.setHasFixedSize(true);
        itemAdapter = new ItemAdapter(JobItemData.items, getContext());
        rv_itemJob.setAdapter(itemAdapter);

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
