package com.stvjuliengmail.githubapi.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Steven on 1/31/2018.
 */

public class RxImageAPIClient {
    // https://rximage.nlm.nih.gov/api/rximage/1/rxnav?name=acetaminophen&rLimit=1
    public static final String BASE_URL = "https://rximage.nlm.nih.gov/api/rximage/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
