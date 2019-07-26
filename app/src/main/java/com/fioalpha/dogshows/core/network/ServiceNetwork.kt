package com.fioalpha.dogshows.core.network

import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.DogsResponse
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupRequest
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupResponse
import io.reactivex.Single
import retrofit2.http.*

interface ServiceNetwork {

    @POST("signup")
    fun makeSignup(@Body signupRequest: SignupRequest): Single<SignupResponse>

    @GET("feed?")
    fun fetchDogs(@Query("category") category: String,  @Header("Authorization") header: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWQzOTRiMDA0NzRkYWEzZDAzYjIwMDgzIiwiaWF0IjoxNTY0MDM1ODQwLCJleHAiOjE1NjUzMzE4NDB9.SXKyPjX0BOkABaCZ9IpS7W9KlKZy5opIjLfCGkTTOLU"): Single<DogsResponse>

}