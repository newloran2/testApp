package com.example.customers.Data;

import android.content.Context;

import com.example.customers.Model.ApiResponse;
import com.example.customers.ViewModel.DataListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManager {


    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/newloran2/testApp/main/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getApiResponse(DataListener listener) {
        CallUsersApi callUsersApi = retrofit.create(CallUsersApi.class);

        Call<ApiResponse> call = callUsersApi.requestUsers();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<ApiResponse.customers> customers = response.body().getCustomers();

                    for (ApiResponse.customers customers1 : customers) {
                        listener.onData(response.body().getCustomers());
                    }

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onError("Request Failed");
            }
        });
    }

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallUsersApi {
        @GET("service.json")
        Call<ApiResponse> requestUsers();
    }
}
