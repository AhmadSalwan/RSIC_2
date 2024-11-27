package com.example.jobfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.R;
import com.example.jobfit.activity.CompanyFactsActivity;
import com.example.jobfit.model.CompanyFacts;

import java.util.ArrayList;

public class CompanyFactsAdapter extends RecyclerView.Adapter<CompanyFactsAdapter.ViewHolder> {
    private ArrayList<CompanyFacts> companyFactsArrayList;

    public CompanyFactsAdapter(ArrayList<CompanyFacts> companyFactsArrayList) {
        this.companyFactsArrayList = companyFactsArrayList;
    }

    @NonNull
    @Override
    public CompanyFactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facts, parent, false);
        return new CompanyFactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyFactsAdapter.ViewHolder holder, int position) {
        CompanyFacts companyFacts = companyFactsArrayList.get(position);
        holder.tv_titleFacts.setText(companyFacts.getFactsTitle());
        holder.iv_imgFacts.setImageResource(companyFacts.getFactsImg());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.context, CompanyFactsActivity.class);
            intent.putExtra("factss", companyFacts);
            holder.context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return companyFactsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_titleFacts;
        ImageView  iv_imgFacts;
        Context context;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_titleFacts = itemView.findViewById(R.id.tv_titleFacts);
            iv_imgFacts = itemView.findViewById(R.id.iv_item_images_facts);
            context = itemView.getContext();
        }
    }
}
