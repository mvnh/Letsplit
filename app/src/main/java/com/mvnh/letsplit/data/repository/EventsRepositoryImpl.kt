package com.mvnh.letsplit.data.repository

import com.mvnh.letsplit.data.network.service.EventsService
import com.mvnh.letsplit.domain.repository.EventsRepository

class EventsRepositoryImpl(private val service: EventsService) : EventsRepository {
    override fun getEvents(userId: String) {
        TODO("Not yet implemented")
    }

    override fun createEvent(userId: String) {
        TODO("Not yet implemented")
    }

    override fun getEventDetails() {
        TODO("Not yet implemented")
    }
}