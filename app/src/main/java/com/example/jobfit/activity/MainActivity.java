package com.example.jobfit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jobfit.fragment.companies;
import com.example.jobfit.fragment.CompassFragment;
import com.example.jobfit.fragment.HomeFragment;
import com.example.jobfit.fragment.ProfileFragment;
import com.example.jobfit.R;

public class MainActivity extends AppCompatActivity {
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();

        Fragment fragment =
                fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();

        }
        LinearLayout navView = findViewById(R.id.navbar);
        RelativeLayout homeIcon = findViewById(R.id.item_beranda);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment());
            }
        });
        RelativeLayout buildingIcon = findViewById(R.id.item_Company1);
        buildingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load the CompanyFragment
                loadFragment(new companies());
            }
        });
        RelativeLayout profileIcon = findViewById(R.id.item_profile);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProfileFragment());
            }
        });

        RelativeLayout compassIcon = findViewById(R.id.item_compass);
        compassIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CompassFragment());
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment); // Replace with your fragment container ID
        fragmentTransaction.commit();
    }
}