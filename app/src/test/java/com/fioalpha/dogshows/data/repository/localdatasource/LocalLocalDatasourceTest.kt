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
        localDatasourceTest.saveToken(User("TOKEN_TEST"))
            .test()
            .assertComplete()
    }

    @Test
    fun `get token with success return token` () {
        localDatasourceTest.saveToken(User("TOKEN_TEST")).subscribe()
        localDatasourceTest.getToken()
            .test()
            .assertComplete()
            .assertValue(User("TOKEN_TEST"))
    }

    @Test
    fun `get token without token saved success return maybe empty` () {
        localDatasourceTest.getToken()
            .test()
            .assertComplete()
            .assertValueCount(0)
    }

}
