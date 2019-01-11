package com.siziksu.payment.data.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class Data {

    @SerializedName("results")
    @Expose
    public List<Character> characters = new ArrayList<>();
}
