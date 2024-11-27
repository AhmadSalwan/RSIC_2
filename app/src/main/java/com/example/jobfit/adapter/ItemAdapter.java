package com.example.jobfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfit.model.Item;
import com.example.jobfit.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.TV_Title.setText(item.getTitle());
        holder.TV_Company.setText(item.getCompany());
//        holder.TV_Description.setText(item.getDescription());
        holder.btn_result.setOnClickListener(v ->{

        });



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView TV_Title, TV_Company, TV_Description;
        Button btn_result;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_Title = itemView.findViewById(R.id.tv_titleitemJD);
            TV_Company = itemView.findViewById(R.id.tv_companyNameitemJD);
            TV_Description = itemView.findViewById(R.id.tv_descSkillItem);
            btn_result = itemView.findViewById(R.id.check_results_button);
        }
    }
}
