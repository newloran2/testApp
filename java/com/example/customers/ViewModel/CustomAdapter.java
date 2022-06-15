package com.example.customers.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customers.Model.ApiResponse;
import com.example.customers.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<ApiResponse.customers> apiResponses;

    public CustomAdapter(Context context, List<ApiResponse.customers> apiResponses) {
        this.context = context;
        this.apiResponses = apiResponses;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(com.example.customers.R.layout.list_users, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


         ApiResponse.customers customers = apiResponses.get(position);
        holder.text_name.setText(customers.getName());
        holder.text_email.setText(customers.getEmail());
        holder.text_status.setText(customers.getStatus());
        holder.text_id.setText(customers.getId());

    }

    @Override
    public int getItemCount() {
        return apiResponses.size();
    }
}
