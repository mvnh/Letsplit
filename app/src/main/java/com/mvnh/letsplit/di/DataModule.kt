package com.mvnh.letsplit.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { androidContext().getSharedPreferences("letsplit", android.content.Context.MODE_PRIVATE) }
}