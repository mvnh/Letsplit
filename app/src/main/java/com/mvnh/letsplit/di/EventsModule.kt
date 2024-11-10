package com.mvnh.letsplit.di

import com.mvnh.letsplit.data.network.service.EventsService
import com.mvnh.letsplit.data.repository.EventsRepositoryImpl
import com.mvnh.letsplit.domain.repository.EventsRepository
import com.mvnh.letsplit.domain.usecases.EventsUseCase
import com.mvnh.letsplit.ui.viewmodel.EventsViewModel
import org.koin.dsl.module

val eventsModule = module {
    single { EventsService(get()) }
    single<EventsRepository> { EventsRepositoryImpl(get()) }
    single { EventsUseCase(get()) }
    single { EventsViewModel(get()) }
}