package com.example.customers.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.customers.Model.ApiResponse;
import com.example.customers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<ApiResponse.customers> apiResponses;

    public ImageAdapter(Context context, List<ApiResponse.customers> apiResponses) {
        this.context = context;
        this.apiResponses = apiResponses;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(com.example.customers.R.layout.list_images, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        ApiResponse.customers customers = apiResponses.get(position);

        if (customers.getProfileImage() != null) {
            Picasso.get().load(apiResponses.get(position).getProfileImage()).into(holder.img_profile);
        }
    }

    @Override
    public int getItemCount() {
        return apiResponses.size();
    }
}
