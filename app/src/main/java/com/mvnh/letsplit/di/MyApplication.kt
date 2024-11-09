package com.mvnh.letsplit.di

import android.app.Application
import androidx.compose.runtime.Composable
import com.mvnh.letsplit.ui.screen.MainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(networkModule, authModule)
        }
    }
}

@Composable
fun App() {
    KoinContext {
        MainScreen()
    }
}