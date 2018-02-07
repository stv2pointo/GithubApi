package com.stvjuliengmail.githubapi.rest;

/**
 * Created by Steven on 1/31/2018.
 */
import com.stvjuliengmail.githubapi.model.RxImageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RxImageEndPoint {
    @GET("/1/rxnav?name={name}&rLimit=1")
    Call<RxImageModel> getRxImageModel(@Path("name") String name);
}
