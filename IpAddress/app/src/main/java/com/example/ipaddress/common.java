package com.example.ipaddress;

import com.example.ipaddress.remote.RetrofitClient;
import com.example.ipaddress.remote.ipService;

public class common {

    private static final String BASE_URL = "http://ip.jsontest.com/";

    public static ipService getIpService(){

        return RetrofitClient.getClient(BASE_URL).create(ipService.class);
    }
}
