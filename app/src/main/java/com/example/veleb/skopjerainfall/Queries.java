package com.example.veleb.skopjerainfall;

import com.example.veleb.skopjerainfall.forecast.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
/**
 * Created by veleb on 13.8.2017.
 */

public interface Queries {
    @Headers("Authorization: key ttn-account-v2.9I1h3H0cas6uEO-yYpR2Kzf_RJPTsue7a5JNIRIAxuI")
    @GET("/api/v2/query")
    Call<List<Data>> loadLastData();
    @Headers("Authorization: key ttn-account-v2.9I1h3H0cas6uEO-yYpR2Kzf_RJPTsue7a5JNIRIAxuI")
    @GET("api/v2/query/{device-id}")
    Call<List<Query>> loadDeviceData(@Query("q") String id);
    @Headers("Authorization: key ttn-account-v2.9I1h3H0cas6uEO-yYpR2Kzf_RJPTsue7a5JNIRIAxuI")
    @GET("/FTA/lora/WEB/index.php?api/v2/devices")
    Call<List<Query>> loadDevices();
    @GET("/data/2.5/forecast?id=785842&appid=b5cc7f55475476fbf63e70ac2ab54ffd&units=metric")
    Call<Forecast> loadForecast();
}

