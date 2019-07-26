package com.fioalpha.dogshows.splash

import com.fioalpha.dogshows.domain.IsLoggedUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SplashViewModelTest {

    private val isLoggedUseCase: IsLoggedUseCase = mockk()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup () {
        splashViewModel = SplashViewModel(isLoggedUseCase)
    }

    @Test
    fun `bind with view model user is logged` () {

        every { isLoggedUseCase.execute() } returns Single.just(true)

        splashViewModel.bind()
//            .delay(3000, TimeUnit.MILLISECONDS)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { it == NextPage.DOG_LIST }
    }

    @Test
    fun `bind with view model user not logged` () {

        every { isLoggedUseCase.execute() } returns Single.just(false)

        splashViewModel.bind()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue { it == NextPage.SIGN_UP }
    }

}