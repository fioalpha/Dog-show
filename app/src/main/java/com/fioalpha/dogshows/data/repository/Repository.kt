package com.fioalpha.dogshows.data.repository

import com.fioalpha.dogshows.data.repository.localdatasource.LocalDatasource
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.transformDomain
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.transformToDomain
import com.fioalpha.dogshows.domain.model.Dog
import com.fioalpha.dogshows.domain.model.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository {

    fun makeSignUp(email: String): Single<User>

    fun fetchDogsByCategory(category: String, user: User): Single<List<Dog>>

    fun fetchToken(): Maybe<User>

    fun saveToken(user: User): Completable

}

class RepositoryImpl(
    private val localDatasource: LocalDatasource,
    private val remoteDataSource: RemoteDataSource
): Repository {

    override fun makeSignUp(email: String): Single<User> = remoteDataSource.makeSignup(email)
        .map { it.transformToDomain() }

    override fun fetchDogsByCategory(category: String, user: User): Single<List<Dog>> = remoteDataSource.fetchDogImages(category, user)
        .map { it.transformDomain()}

    override fun fetchToken(): Maybe<User> = localDatasource.getToken()

    override fun saveToken(user: User): Completable = localDatasource.saveToken(user)

}