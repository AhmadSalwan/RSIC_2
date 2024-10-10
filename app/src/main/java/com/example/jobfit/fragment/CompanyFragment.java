package com.example.jobfit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.model.Company;
import com.example.jobfit.adapter.CompanyAdapter;
import com.example.jobfit.R;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {

    private RecyclerView rvCompany, rvCompanyFacts;
    private CompanyAdapter companyFactsAdapter;
    private List<Company> companyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);

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
