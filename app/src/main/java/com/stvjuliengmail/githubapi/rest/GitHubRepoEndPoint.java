package com.stvjuliengmail.githubapi.rest;

import java.util.List;

import com.stvjuliengmail.githubapi.model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Steven on 1/29/2018.
 */

public interface GitHubRepoEndPoint {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
}
