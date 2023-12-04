package com.jessz.cleanarchitectureexample.core.application

import android.app.Application
import com.jessz.cleanarchitectureexample.features.advice.di.AdviceModule

class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        provideModules()
    }

    private fun provideModules() {
        AdviceModule.provideAdviceModule()
    }

}