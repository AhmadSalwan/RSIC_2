package com.example.jobfit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jobfit.R;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder>{
    private List<String> roles;

    public RoleAdapter(List<String> roles) {
        this.roles = roles;
    }
    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_role_available, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        holder.roleName.setText(roles.get(position));
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    public static class RoleViewHolder extends RecyclerView.ViewHolder {
        TextView roleName;

        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            roleName = itemView.findViewById(R.id.tv_role_available);
        }
    }
}
