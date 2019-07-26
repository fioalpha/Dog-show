package com.fioalpha.dogshows.list

import com.fioalpha.dogshows.domain.model.Dog

sealed class DogListViewState {
    object Loading : DogListViewState()
    data class Success(val dogs: List<Dog>) : DogListViewState()
    data class Failure(val error: Throwable) : DogListViewState()
}