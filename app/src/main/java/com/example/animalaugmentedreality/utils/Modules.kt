package com.example.animalaugmentedreality.utils

import com.example.animalaugmentedreality.views.splashscreen.SplashScreenActivity
import org.koin.dsl.module

val module = module {

    single { SplashScreenActivity() }

    single { SessionManager(get()) }
}