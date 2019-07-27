package com.fioalpha.dogshows.data.repository

import com.fioalpha.dogshows.core.network.ServiceNetwork
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.DogsResponse
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupRequest
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupResponse
import com.fioalpha.dogshows.domain.model.User
import io.reactivex.Single

interface RemoteDataSource {

    fun fetchDogImages(category: String, user: User): Single<DogsResponse>

    fun makeSignup(email: String): Single<SignupResponse>

}


class RemoteDataSourceImpl(
    private val service: ServiceNetwork
): RemoteDataSource {
    override fun fetchDogImages(category: String, user: User): Single<DogsResponse> {
        return service.fetchDogs(category, user.token)
    }

    override fun makeSignup(email: String): Single<SignupResponse> {
        return service.makeSignup(SignupRequest.create(email))
    }

}
