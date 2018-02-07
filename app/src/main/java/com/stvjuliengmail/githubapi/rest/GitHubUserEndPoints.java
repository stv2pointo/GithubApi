package com.stvjuliengmail.githubapi.rest;

import com.stvjuliengmail.githubapi.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Steven on 1/29/2018.
 */

public interface GitHubUserEndPoints {
    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);
}
