package com.duman.userprofile

import android.app.Application
import com.duman.userprofile.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App() : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(vmModule))
        }
    }
}