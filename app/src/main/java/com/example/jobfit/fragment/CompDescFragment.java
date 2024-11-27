package com.example.jobfit.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobfit.R;
import com.example.jobfit.adapter.RoleAdapter;
import com.example.jobfit.model.Company;


public class CompDescFragment extends Fragment {

    private ImageView companyLogo, companyOffice;
    private TextView companyName, companyLocation, companyDescription;
    private RecyclerView rv_Role;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comp_desc, container, false);

        companyOffice = view.findViewById(R.id.iv_company_office_desc);
        companyLogo = view.findViewById(R.id.iv_company_logo_desc);
        companyName = view.findViewById(R.id.tv_companyNameDesc);
        companyLocation = view.findViewById(R.id.tv_companyLocationDesc);
        companyDescription = view.findViewById(R.id.tv_companyDescriptionDesc);
        rv_Role = view.findViewById(R.id.rv_role_available);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Company company = bundle.getParcelable("company");
            companyOffice.setImageResource(company.getKantor_img());
            companyLogo.setImageResource(company.getLogo());
            companyName.setText(company.getCompanyTitle());
            companyLocation.setText(company.getAddress());
            companyDescription.setText(company.getAbout());

            RoleAdapter roleAdapter = new RoleAdapter(company.getRole());
            rv_Role.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_Role.setAdapter(roleAdapter);
        }
        return view;
    }
}