package com.fioalpha.dogshows.data.repository

import com.fioalpha.dogshows.domain.model.Dog
import com.fioalpha.dogshows.domain.model.Token
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {

    fun isLogged(): Single<Boolean>

    fun makeSignUp(email: String): Single<Token>

    fun fetchDogsByCategory(category: String): Single<Dog>

    fun fetchToken(): Single<Token>

    fun saveToken(): Completable

}