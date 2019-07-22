package com.fioalpha.dogshows.data.repository.localdatasource

import com.fioalpha.dogshows.domain.model.User
import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalDatasource {

    fun saveToken(user: User): Completable
    fun getToken(): Maybe<User>

}

class LocalDatasourceImpl(

): LocalDatasource {

    private lateinit var user: User

    override fun getToken(): Maybe<User> {
       return if(::user.isInitialized) {
           Maybe.just(user)
       } else {
           Maybe.empty<User>()
       }
    }

    override fun saveToken(user: User): Completable {
        this.user = user
        return Completable.complete()
    }

}