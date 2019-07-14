package com.fioalpha.dogshows.data.repository.retrofitdatasource.model


data class SignupResponse (
    val user: UserResponse?
)

data class UserResponse (
    val email: String?,
    val token: String?
)