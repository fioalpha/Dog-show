package com.fioalpha.dogshows.domain

import com.fioalpha.dogshows.data.repository.Repository
import com.fioalpha.dogshows.domain.model.Dog
import io.reactivex.Single

interface GetDogsUseCase {

    fun execute(category: String): Single<List<Dog>>

}

class GetDogsUseCaseImpl(
    private val repository: Repository
): GetDogsUseCase {

    override fun execute(category: String): Single<List<Dog>> = repository.fetchDogsByCategory(category)

}