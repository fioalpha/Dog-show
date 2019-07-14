package com.fioalpha.dogshows.data.repository.retrofitdatasource.model

import com.google.gson.annotations.SerializedName

data class DogsResponse (
    val category: String,
    @SerializedName("list") val images: List<String>
)