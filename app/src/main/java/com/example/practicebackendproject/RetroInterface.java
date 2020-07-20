package com.example.practicebackendproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {
    @GET("greeting")
    Call<DemoItem> getDemoItem();
}
