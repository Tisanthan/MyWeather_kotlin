package com.example.ipaddress.remote;

import com.example.ipaddress.model.ip;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ipService {
    @GET("/")
    Call<ip> getIp();
}
