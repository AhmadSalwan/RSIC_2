package com.example.jobfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.activity.CompanyActivity;
import com.example.jobfit.activity.CompanyFactsActivity;
import com.example.jobfit.model.Company;
import com.example.jobfit.R;
import com.example.jobfit.model.CompanyFacts;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private ArrayList<Company> companies;

    public CompanyAdapter(ArrayList<Company> companies) {
        this.companies = companies;
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item layout (item_company.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        return new CompanyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.imgCompanyLogo.setImageResource(company.getLogo());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.context, CompanyActivity.class);
            intent.putExtra("companiesss", company);
            holder.context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCompanyLogo;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCompanyLogo = itemView.findViewById(R.id.iv_logocompany);
            context = itemView.getContext();
        }
    }
}
