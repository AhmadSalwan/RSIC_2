package com.example.jobfit;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

public class HomeFragment extends Fragment {

    private DBHelper dbHelper;
    private ImageView imageViewUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getContext()); // Inisialisasi DBHelper
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisialisasi Toolbar
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
