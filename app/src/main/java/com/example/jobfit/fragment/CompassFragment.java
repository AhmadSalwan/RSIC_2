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
import android.widget.TextView;

import com.example.jobfit.adapter.ItemAdapter;
import com.example.jobfit.db.DBHelper;
import com.example.jobfit.R;
import com.example.jobfit.db.JobItemData;

public class CompassFragment extends Fragment {

    private String cardData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        // Get the card data passed from HomeFragment
        if (getArguments() != null) {
            cardData = getArguments().getString("card_data");
        }

        // Use the cardData to display in CompassFragment (e.g., show details)
        TextView cardDetailsText = view.findViewById(R.id.cardDetailsText);
        cardDetailsText.setText(cardData); // Example to show data in TextView

        return view;
    }
}

