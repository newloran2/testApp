package com.example.customers.ViewModel;


import com.example.customers.Model.ApiResponse;

import java.util.List;

public interface DataListener<ListResponse> {
    void onData(List<ApiResponse.customers>list);
    void onError (String message);
}
