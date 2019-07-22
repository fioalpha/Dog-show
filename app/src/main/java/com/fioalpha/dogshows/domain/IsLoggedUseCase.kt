package com.fioalpha.dogshows.domain

import com.fioalpha.dogshows.data.repository.Repository
import io.reactivex.Single


interface IsLoggedUseCase {
    fun execute(): Single<Boolean>
}

class IsLoggedUseCaseImpl(
    private val repository: Repository
): IsLoggedUseCase {

    private val NOT_LOGGED = false

    override fun execute(): Single<Boolean> = repository.fetchToken().isEmpty
        .flatMap { Single.just(it.not()) }
        .onErrorResumeNext { Single.just(NOT_LOGGED) }

}
