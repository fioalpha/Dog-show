package com.fioalpha.dogshows.data.repository.retrofitdatasource.model

import com.fioalpha.dogshows.domain.model.User


data class SignupResponse (
    val user: UserResponse?
)

data class UserResponse (
    val email: String?,
    val token: String?
)

fun SignupResponse.transformToDomain(): User = User(
    email = user?.email?: "",
    token = user?.token?: ""
)