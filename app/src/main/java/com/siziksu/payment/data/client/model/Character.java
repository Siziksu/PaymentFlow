package com.siziksu.payment.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Character {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("thumbnail")
    @Expose
    public Thumbnail thumbnail;
}
