package com.fioalpha.dogshows.signup

import android.util.Patterns
import com.fioalpha.dogshows.data.repository.Repository
import com.fioalpha.dogshows.domain.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.Exception

class SignUpViewModel (
    private val repository: Repository
){

    val viewState: BehaviorSubject<SignUpViewState> =  BehaviorSubject.create()

    fun makeSignUp(email: String): Completable {

        if(email.isBlank()) {
            viewState.onNext(SignUpViewState.Failure(Throwable("Campo do email nao pode ser vazio")))
            return Completable.complete()
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.onNext(SignUpViewState.Failure(Throwable("Formato do email errado")))
            return Completable.complete()
        }

        return repository.makeSignUp(email).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapCompletable { repository.saveToken(it) }
            .doOnTerminate { viewState.onNext(SignUpViewState.Success) }
            .onErrorResumeNext{
                viewState.onNext(SignUpViewState.Failure(it))
                Completable.complete()
            }
    }

}


sealed class SignUpViewState {
    object Loading: SignUpViewState()
    object Success: SignUpViewState()
    data class Failure(val error: Throwable): SignUpViewState()
}

