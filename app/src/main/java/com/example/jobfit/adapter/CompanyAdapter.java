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

import com.example.jobfit.model.Company;
import com.example.jobfit.R;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private ArrayList<Company> companies;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Company company);
    }

    public CompanyAdapter(ArrayList<Company> companies, OnItemClickListener listener) {
        this.companies = companies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item layout (item_company.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.bind(company);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCompanyLogo;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCompanyLogo = itemView.findViewById(R.id.iv_logocompany);
        }

        public void bind(final Company company) {
            imgCompanyLogo.setImageResource(company.getLogo());
            itemView.setOnClickListener(v -> listener.onItemClick(company));
        }
    }
}
