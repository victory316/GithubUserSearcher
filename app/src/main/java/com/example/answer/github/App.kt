package com.example.answer.github

import android.app.Application
import com.example.answer.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {

        // Timber 설정
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        super.onCreate()
    }
}