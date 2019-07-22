package com.fioalpha.dogshows.data.repository.retrofitdatasource.model

import com.fioalpha.dogshows.domain.model.Dog
import com.google.gson.annotations.SerializedName

data class DogsResponse (
    val category: String,
    @SerializedName("list") val images: List<String>
)
fun DogsResponse.transformDomain(): List<Dog> = images.map { Dog(it) }