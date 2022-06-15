package com.example.customers.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customers.Data.RequestManager;
import com.example.customers.Model.ApiResponse;
import com.example.customers.ViewModel.CustomAdapter;
import com.example.customers.ViewModel.DataListener;
import com.example.customers.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        RequestManager requestManager = new RequestManager(getActivity());
        requestManager.getApiResponse(listener);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    private final DataListener<ApiResponse.customers> listener = new DataListener<ApiResponse.customers>() {
        @Override
        public void onData (List<ApiResponse.customers> list){
            showNews(list);
        }
        @Override
        public void onError(String message) {

        }
    };
    private void showNews(List<ApiResponse.customers> list) {
        recyclerView = binding.recyclerMain;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new CustomAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}