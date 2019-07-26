package com.fioalpha.dogshows.core

import android.app.Application
import com.fioalpha.dogshows.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ApplicatonCustom: Application() {

    override fun onCreate() {
        super.onCreate()
        setupInjection()
    }

    protected fun setupInjection() {
        startKoin {
            androidContext(this@ApplicatonCustom)
            modules(arrayListOf(allModules))
        }
    }

}