package com.fioalpha.dogshows.data.repository.retrofitdatasource.model

data class SignupRequest (
    val email: String
) {
    companion object {
        fun create(email: String) = SignupRequest(email)
    }
}