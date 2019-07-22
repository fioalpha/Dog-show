package com.fioalpha.dogshows.data.repository

import com.fioalpha.dogshows.core.network.ServiceNetwork
import com.fioalpha.dogshows.data.repository.retrofitdatasource.model.SignupResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest {

    private val serviceNetwork: ServiceNetwork = mockk()
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSourceImpl(serviceNetwork)
    }

    @Test
    fun `make signup with success`() {
        every { serviceNetwork.makeSignup(any()) } returns Single.just(Gson().toParse(SINGUP_RESPONSE_JSON))

        remoteDataSource.makeSignup("teste@teste.com")
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun `make signup with error`() {
        every { serviceNetwork.makeSignup(any()) } returns Single.error(Exception())

        remoteDataSource.makeSignup("teste@teste.com")
            .test()
            .assertError{ it is Exception }
            .assertNotComplete()
    }

}

inline fun <reified T> Gson.toParse(text: String): T {
    return this.fromJson(text, object: TypeToken<T>() {}.type)
}

val SINGUP_RESPONSE_JSON = "{\"user\":{\"_id\":\"5d2aaeb829ed3ffd98b215e3\",\"email\":\"fioalpha@gmail.com\",\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWQyYWFlYjgyOWVkM2ZmZDk4YjIxNWUzIiwiaWF0IjoxNTYzMDc4MzI4LCJleHAiOjE1NjQzNzQzMjh9.zkTGLuCdsfc1d0MU9QVvJfv_X5RQjZnjSOcrN2o_3Uw\",\"createdAt\":\"2019-07-14T04:25:28.506Z\",\"updatedAt\":\"2019-07-14T04:25:28.506Z\",\"__v\":0}}"