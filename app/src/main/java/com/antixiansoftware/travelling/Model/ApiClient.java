package com.antixiansoftware.travelling.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.43.79/traveller/";
    private static Retrofit retrofit=null;


    public static Retrofit getApiClient(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.MINUTES) // write timeout
                .connectTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor(logging)
                .build();
//

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }


}

