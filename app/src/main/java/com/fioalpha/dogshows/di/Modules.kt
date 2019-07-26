package com.fioalpha.dogshows.di

import com.fioalpha.dogshows.core.network.RetrofitService
import com.fioalpha.dogshows.core.network.ServiceNetwork
import com.fioalpha.dogshows.data.repository.RemoteDataSource
import com.fioalpha.dogshows.data.repository.RemoteDataSourceImpl
import com.fioalpha.dogshows.data.repository.Repository
import com.fioalpha.dogshows.data.repository.RepositoryImpl
import com.fioalpha.dogshows.data.repository.localdatasource.LocalDatasource
import com.fioalpha.dogshows.data.repository.localdatasource.LocalDatasourceImpl
import com.fioalpha.dogshows.domain.GetDogsUseCase
import com.fioalpha.dogshows.domain.GetDogsUseCaseImpl
import com.fioalpha.dogshows.domain.IsLoggedUseCase
import com.fioalpha.dogshows.domain.IsLoggedUseCaseImpl
import com.fioalpha.dogshows.list.DogListViewModel
import com.fioalpha.dogshows.signup.SignUpViewModel
import com.fioalpha.dogshows.splash.SplashViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

var allModules = module {
    factory { SplashViewModel(get()) }
    factory<IsLoggedUseCase> { IsLoggedUseCaseImpl(get()) }
    single<Repository> { RepositoryImpl(get(), get()) }
    single<LocalDatasource> { LocalDatasourceImpl(get(), get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single { GsonBuilder().create()}
    single<ServiceNetwork> { RetrofitService().retrofit(get()).create(ServiceNetwork::class.java)}
    factory { SignUpViewModel(get()) }
    factory { DogListViewModel(get()) }
    factory<GetDogsUseCase> { GetDogsUseCaseImpl(get()) }
}