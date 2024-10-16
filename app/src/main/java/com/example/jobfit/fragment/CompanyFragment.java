package com.example.jobfit.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.db.DBHelper;
import com.example.jobfit.model.Company;
import com.example.jobfit.adapter.CompanyAdapter;
import com.example.jobfit.R;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {
    ImageView imageViewUser;
    private DBHelper dbHelper;
    private RecyclerView rvCompany, rvCompanyFacts;
    private CompanyAdapter companyFactsAdapter;
    private List<Company> companyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageViewUser = toolbar.findViewById(R.id.head_image);
        String email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);
        dbHelper = new DBHelper(getContext());

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
        // Inisialisasi RecyclerView
        rvCompanyFacts = view.findViewById(R.id.rv_companyfacts);
        rvCompanyFacts.setHasFixedSize(true);
        rvCompanyFacts.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inisialisasi data
        companyList = new ArrayList<>();
        companyList.add(new Company("Company 1", "Description for Company 1"));
        companyList.add(new Company("Company 2", "Description for Company 2"));
        companyList.add(new Company("Company 3", "Description for Company 3"));

        // Set Adapter
        companyFactsAdapter = new CompanyAdapter(companyList);
        rvCompanyFacts.setAdapter(companyFactsAdapter);



        return view;
    }
}
