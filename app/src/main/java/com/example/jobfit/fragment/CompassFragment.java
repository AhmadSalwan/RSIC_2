package com.example.jobfit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jobfit.R;

public class CompassFragment extends Fragment {

    private String cardData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);

        // Retrieve the data from the arguments
        if (getArguments() != null) {
            cardData = getArguments().getString("card_data");
        }

        // Find the TextView to display the data
        TextView textView = view.findViewById(R.id.cardDetailsText);

        // Display the cardData in the TextView
        if (cardData != null) {
            textView.setText(cardData);
        } else {
            textView.setText("No data received");
        }

        return view;
    }
}
