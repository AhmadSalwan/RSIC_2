package com.example.jobfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private List<Company> companyList;

    public CompanyAdapter(List<Company> companyList) {
        this.companyList = companyList;
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
        // Set data to item layout
        Company company = companyList.get(position);
        holder.titleTextView.setText(company.getTitle());
        holder.descriptionTextView.setText(company.getDescription());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    // ViewHolder class
    public class CompanyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_title);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
        }
    }
}
