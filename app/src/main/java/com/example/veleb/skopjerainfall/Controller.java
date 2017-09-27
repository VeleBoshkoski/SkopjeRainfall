package com.example.veleb.skopjerainfall;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by veleb on 13.8.2017.
 */

public class Controller implements Callback<List<Data>> {

    static final String BASE_URL = "https://sun-and-rain.data.thethingsnetwork.org";
    public List<Data> data = new ArrayList<>();

    public Controller() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Queries queriesAPI = retrofit.create(Queries.class);

        Call<List<Data>> call = queriesAPI.loadLastData();

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
        if(response.isSuccessful()) {
            List<Data> queryList = response.body();
            for (Data data : queryList) {
                Log.d("Stuff:", data.getDeviceId());
            }
            data = queryList;
            synchronized(data) {
                data.notifyAll();
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Data>> call, Throwable t) {
        Log.e("peder", t.toString());
        t.printStackTrace();
    }
}