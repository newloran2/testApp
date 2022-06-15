package com.example.customers.ViewModel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.customers.R;
import com.example.customers.databinding.ActivityMainBinding;
import com.example.customers.databinding.FragmentHomeBinding;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_name;
    TextView text_email;
    TextView text_status;
    TextView text_id;
    ImageView img_profile;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);


        text_name = itemView.findViewById(com.example.customers.R.id.text_name);
        text_email = itemView.findViewById(R.id.text_email);
        text_status = itemView.findViewById(R.id.text_status);
        text_id = itemView.findViewById(R.id.text_id);
        img_profile = itemView.findViewById(R.id.img_profile);
    }
}
