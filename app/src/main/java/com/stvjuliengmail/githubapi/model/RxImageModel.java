package com.stvjuliengmail.githubapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Steven on 1/31/2018.
 */

public class RxImageModel {
    @SerializedName("nlmRxImages/imageUrl")
    private String rxImageUrl;
    @SerializedName("nlmRxImages/name")
    private String name;

    public RxImageModel(String rxImageUrl, String name) {
        this.rxImageUrl = rxImageUrl;
        this.name = name;
    }

    public String getRxImage() {
        return rxImageUrl;
    }

    public void setRxImage(String rxImageUrl) {
        this.rxImageUrl = rxImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
