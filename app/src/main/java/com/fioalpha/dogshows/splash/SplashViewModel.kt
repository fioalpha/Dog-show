package com.fioalpha.dogshows.splash

import com.fioalpha.dogshows.domain.IsLoggedUseCase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.SingleSubject
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val isLogged: IsLoggedUseCase
) {

    fun bind(): Single<NextPage> = isLogged.execute()
        .map { isLogged ->
            if(isLogged) NextPage.DOG_LIST
            else NextPage.SIGN_UP
        }

}


enum class NextPage {
    SIGN_UP, DOG_LIST, FULL_SCREEN
}
