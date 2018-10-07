package io.adsonwheels.adsonwheels.utils;

import android.content.SharedPreferences;

import io.adsonwheels.adsonwheels.models.LoginRequest;
import io.adsonwheels.adsonwheels.models.LoginResponse;
import io.adsonwheels.adsonwheels.models.SignupRequest;
import io.adsonwheels.adsonwheels.models.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
    @Headers({
            "Api-Endpoint: login",
    })
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @Headers({
            "Api-Endpoint: signup",
    })
    @POST("signup")
    Call<SignupResponse> doSignup(@Body SignupRequest signupRequest);
}
