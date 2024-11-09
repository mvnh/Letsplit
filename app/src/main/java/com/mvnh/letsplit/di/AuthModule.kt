package com.mvnh.letsplit.di

import com.mvnh.letsplit.data.network.service.AuthService
import com.mvnh.letsplit.data.repository.AuthRepositoryImpl
import com.mvnh.letsplit.domain.repository.AuthRepository
import com.mvnh.letsplit.domain.usecases.AuthUseCase
import com.mvnh.letsplit.ui.viewmodel.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { AuthUseCase(get()) }

    viewModel { AuthViewModel(get()) }
}