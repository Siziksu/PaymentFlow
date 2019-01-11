package com.siziksu.payment.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siziksu.payment.BuildConfig;
import com.siziksu.payment.data.Repository;
import com.siziksu.payment.data.RepositoryContract;
import com.siziksu.payment.data.client.MarvelClient;
import com.siziksu.payment.data.persistence.android.ContactClient;
import com.siziksu.payment.data.persistence.android.ContactClientContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class DataModule {

    private static final String MARVEL_URI = "http://gateway.marvel.com:80";
    private static final int CONNECTION_TIMEOUT = 15;

    @Singleton
    @Provides
    RepositoryContract providesRepository() {
        return new Repository();
    }

    @Singleton
    @Provides
    ContactClientContract providesContactClient() {
        return new ContactClient();
    }

    @Provides
    @Singleton
    Cache providesOkHttpCache(Context context) {
        final int cacheSize = 10485760; // 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient.Builder providesOkHttpClient(Cache cache) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.cache(cache);
        return okHttpClient;
    }

    @Provides
    @Singleton
    Converter.Factory providesGsonConverterFactory() {
        Gson gson = new GsonBuilder().create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    @Named("Marvel Retrofit")
    Retrofit providesRetrofit(OkHttpClient.Builder okHttpClient, Converter.Factory factoryConverter) {
        return getRetrofitBuilder(MARVEL_URI, okHttpClient, factoryConverter).build();
    }

    @Provides
    @Singleton
    MarvelClient providesMarvelClient(@Named("Marvel Retrofit") Retrofit retrofit) {
        return retrofit.create(MarvelClient.class);
    }

    private Retrofit.Builder getRetrofitBuilder(final String uri, OkHttpClient.Builder okHttpClient, Converter.Factory factoryConverter) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(logging);
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(factoryConverter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(uri);
        return builder.client(okHttpClient.build());
    }
}
