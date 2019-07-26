package com.fioalpha.dogshows.domain

import com.fioalpha.dogshows.data.repository.Repository
import com.fioalpha.dogshows.domain.model.User
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

class IsLoggedUseCaseTest {

    val USER_CREATE_MOCK = User("test@test.com", "TOKEN_TEST")

    private val repository: Repository = mockk()

    private lateinit var useCase: IsLoggedUseCase

    @Before
    fun setup() {
        useCase = IsLoggedUseCaseImpl(repository)
    }


    @Test
    fun `is logged with success`() {

        every { repository.fetchToken() } returns Maybe.just(USER_CREATE_MOCK)

        useCase.execute()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { it }
    }

    @Test
    fun `is logged with error`() {

        every { repository.fetchToken() } returns Maybe.error(Exception())

        useCase.execute()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { !it }
    }

    @Test
    fun `not logged with success`() {

        every { repository.fetchToken() } returns Maybe.empty()

        useCase.execute()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { !it }
    }

}