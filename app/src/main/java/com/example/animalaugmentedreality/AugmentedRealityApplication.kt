package com.example.animalaugmentedreality

import android.app.Application
import com.example.animalaugmentedreality.utils.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AugmentedRealityApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AugmentedRealityApplication)
            modules(module)
        }
    }
}