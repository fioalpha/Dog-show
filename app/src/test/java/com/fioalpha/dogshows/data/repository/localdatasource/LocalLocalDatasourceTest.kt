package com.fioalpha.dogshows.data.repository.localdatasource

import com.fioalpha.dogshows.domain.model.User
import org.junit.Before
import org.junit.Test

class LocalDatasourceTest {

    private lateinit var localDatasourceTest: LocalDatasourceImpl

    @Before
    fun setup() {
        localDatasourceTest = LocalDatasourceImpl()
    }

    @Test
    fun `save token with success return completed`() {
        localDatasourceTest.saveToken(USER_CREATE_MOCK)
            .test()
            .assertComplete()
    }

    @Test
    fun `get token with success return token` () {
        localDatasourceTest.saveToken(USER_CREATE_MOCK).subscribe()
        localDatasourceTest.getToken()
            .test()
            .assertComplete()
            .assertValue(USER_CREATE_MOCK)
    }

    @Test
    fun `get token without token saved success return maybe empty` () {
        localDatasourceTest.getToken()
            .test()
            .assertComplete()
            .assertValueCount(0)
    }

}

val USER_CREATE_MOCK = User("test@test.com", "TOKEN_TEST")