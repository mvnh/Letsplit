package com.mvnh.letsplit.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mvnh.letsplit.domain.model.EventDetails
import com.mvnh.letsplit.domain.usecases.EventsUseCase

class EventsViewModel(private val useCase: EventsUseCase) : ViewModel() {
    fun getEvents(): List<EventDetails> {
        return useCase.getEvents()
    }

    fun createEvent() {

    }
}