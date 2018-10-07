package io.adsonwheels.adsonwheels.utils;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class APIClient {
    private static final String BASE_URL = "https://adsapi.trycatchajith.com/v1.0/";
    private static Retrofit retrofit = null;

     public static Retrofit getClient (Context context){
        APIHeaderInterceptor apiHeaderInterceptor = new APIHeaderInterceptor(context);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(apiHeaderInterceptor);
        httpClient.addInterceptor(interceptor);

        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }



}
