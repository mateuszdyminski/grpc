package com.grpc;

import com.grpc.models.Req;
import com.grpc.models.Res;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoadApi {
    @POST("/load")
    Call<Res> load(@Body Req r);
}