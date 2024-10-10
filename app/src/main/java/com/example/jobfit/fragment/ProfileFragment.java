package com.example.jobfit.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jobfit.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the button by its ID
        Button editButton = view.findViewById(R.id.continue_editbtn);

        // Set an OnClickListener to handle button clicks
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the fragment you want to switch to
                EditResumeFragment editResumeFragment = new EditResumeFragment();

                // Get the FragmentManager and start a transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment
                fragmentTransaction.replace(R.id.main, editResumeFragment);

                // Optionally, add this transaction to the back stack so the user can navigate back
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
    }
}
