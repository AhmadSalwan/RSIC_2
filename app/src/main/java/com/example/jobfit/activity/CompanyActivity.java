package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.R;
import com.example.jobfit.adapter.RoleAdapter;
import com.example.jobfit.model.Company;

public class CompanyActivity extends AppCompatActivity {
    private ImageView companyLogo, companyOffice;
    private TextView companyName, companyLocation, companyDescription;
    private RecyclerView rv_Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_company);

        companyOffice = findViewById(R.id.iv_company_office_desc);
        companyLogo = findViewById(R.id.iv_company_logo_desc);
        companyName = findViewById(R.id.tv_companyNameDesc);
        companyLocation = findViewById(R.id.tv_companyLocationDesc);
        companyDescription = findViewById(R.id.tv_companyDescriptionDesc);
        rv_Role = findViewById(R.id.rv_role_available);

        Intent intent = getIntent();
        Company company = intent.getParcelableExtra("companiesss");

        companyOffice.setImageResource(company.getKantor_img());
        companyLogo.setImageResource(company.getLogo());
        companyName.setText(company.getCompanyTitle());
        companyLocation.setText(company.getAddress());
        companyDescription.setText(company.getAbout());

        RoleAdapter adapter = new RoleAdapter(company.getRole()); // Roles dari Company
        rv_Role.setLayoutManager(new LinearLayoutManager(this));
        rv_Role.setAdapter(adapter);
    }
}