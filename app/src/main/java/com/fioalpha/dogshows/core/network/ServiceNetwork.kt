package com.fioalpha.dogshows.core.network

import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.DogsResponse
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupRequest
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceNetwork {

    @POST("signup")
    fun makeSignup(signupRequest: SignupRequest): Single<SignupResponse>

    @GET("feed?category={category}")
    fun fetchDogs(@Query("category") category: String): Single<DogsResponse>

}