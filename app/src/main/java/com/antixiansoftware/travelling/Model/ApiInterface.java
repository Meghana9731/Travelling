package com.antixiansoftware.travelling.Model;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {



    @GET("vehicals.php")
    Call<List<Trvcollection>>getTr();


    @FormUrlEncoded
    @POST("vehicals.php")
    Call<Trvcollection> insertDaata(
            @Field("id") int id,
            @Field("vehical_name") String vehical_name,
            @Field("seating_capacity") String seating_capacity,
            @Field("km") String km);



}