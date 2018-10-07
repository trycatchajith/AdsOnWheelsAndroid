package io.adsonwheels.adsonwheels.utils;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import io.adsonwheels.adsonwheels.config.AdsOnWheelsPref;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class APIHeaderInterceptor  implements Interceptor {
    private AdsOnWheelsPref mpref;
    private Context context;
    public APIHeaderInterceptor(Context context){
        this.context = context;
    }

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {

        Request original = chain.request();

        String endPoint = original.header("Api-Endpoint");

        if(endPoint.equals("login") || endPoint.equals("signup")){
            Request request = original.newBuilder()
                    .header("Ads-On-Wheels-Api-Key","ivFfWYNtvcqD2SpjFFaZcO4DHKVDGYKPkfU41vnmxBNnh0vLwvAw7sZE0K7xxFcH")
                    .header("Content-Type", "application/json")
                    .removeHeader("Api-Endpoint")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }else{
            AdsOnWheelsPref adsPref = new AdsOnWheelsPref(context);
            String session_token = adsPref.getLoginSessionToken();
            Request request = original.newBuilder()
                    .header("Ads-On-Wheels-Session-Token", session_token)
                    .header("Ads-On-Wheels-Api-Key","ivFfWYNtvcqD2SpjFFaZcO4DHKVDGYKPkfU41vnmxBNnh0vLwvAw7sZE0K7xxFcH")
                    .header("Content-Type", "application/json")
                    .removeHeader("Api-Endpoint")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }

    }
}
