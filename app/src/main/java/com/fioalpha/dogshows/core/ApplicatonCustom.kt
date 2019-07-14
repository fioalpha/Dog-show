package com.fioalpha.dogshows.core

import android.app.Application
import com.fioalpha.dogshows.core.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ApplicatonCustom: Application() {

    protected fun setupInjection() {
        startKoin {
            androidContext(this@ApplicatonCustom)
            modules(modules)
        }
    }

}

val modules: List<Module> = arrayListOf(
    networkModule
)
