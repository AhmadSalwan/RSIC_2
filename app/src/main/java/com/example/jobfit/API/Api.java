package com.example.jobfit.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("match_job")
    Call<List<Job>> matchJob(@Body UserInput userInput);
}
