package com.fioalpha.dogshows.list

import com.fioalpha.dogshows.domain.GetDogsUseCase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class DogListViewModel(
    private val getDogsUseCase: GetDogsUseCase
) {

    val viewModel: BehaviorSubject<DogListViewState> =
        BehaviorSubject.create()

    fun getDogsByCategory(category: String): Completable {
        viewModel.onNext(DogListViewState.Loading)
        return getDogsUseCase.execute(category)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { viewModel.onNext(DogListViewState.Success(it)) }
            .onErrorReturn {
                viewModel.onNext(DogListViewState.Loading)
                listOf()
            }
            .flatMapCompletable { Completable.complete() }
    }

}