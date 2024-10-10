package com.example.jobfit;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {

    private RecyclerView rvCompany;
    private CompanyAdapter companyAdapter;
    private List<Company> companyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        // Inisialisasi RecyclerView
        rvCompany = view.findViewById(R.id.rv_company);
        rvCompany.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inisialisasi data
        companyList = new ArrayList<>();
        companyList.add(new Company("Company 1", "Description for Company 1"));
        companyList.add(new Company("Company 2", "Description for Company 2"));
        companyList.add(new Company("Company 3", "Description for Company 3"));

        // Set Adapter
        companyAdapter = new CompanyAdapter(companyList);
        rvCompany.setAdapter(companyAdapter);

        return view;
    }
}
