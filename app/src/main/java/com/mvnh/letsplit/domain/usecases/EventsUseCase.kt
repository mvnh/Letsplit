package com.mvnh.letsplit.domain.usecases

import com.mvnh.letsplit.domain.model.EventDetails
import com.mvnh.letsplit.domain.repository.EventsRepository
import com.mvnh.letsplit.ui.viewmodel.state.BalanceStatus
import java.time.LocalDate

class EventsUseCase(private val repository: EventsRepository) {
    fun getEvents(): List<EventDetails> {
        // Stub data for getEvents
        return listOf(
            EventDetails(
                title = "Event 1",
                deadline = LocalDate.now().plusDays(10),
                targetAmount = 1000,
                collectedAmount = 500,
                balanceStatus = BalanceStatus.Positive
            ),
            EventDetails(
                title = "Event 2",
                deadline = LocalDate.now().plusDays(20),
                targetAmount = 2000,
                collectedAmount = 1500,
                balanceStatus = BalanceStatus.Negative
            )
        )
    }

    fun createEvent(eventDetails: EventDetails): Boolean {
        // Stub implementation for createEvent
        return true
    }
}
