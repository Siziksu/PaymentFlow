package com.siziksu.payment.data.client;

import com.siziksu.payment.data.client.model.Characters;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelClient {

    String URI = "/v1/public/";
    String URI_GET_CHARACTERS = URI + "characters";

    @GET(URI_GET_CHARACTERS)
    Single<Characters> getCharacters(
            @Query("ts") Long ts,
            @Query("hash") String hash,
            @Query("apikey") String apiKey,
            @Query("nameStartsWith") String name,
            @Query("offset") Integer offset,
            @Query("limit") Integer limit
    );
}
