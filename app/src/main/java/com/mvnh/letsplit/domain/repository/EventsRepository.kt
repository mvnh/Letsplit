package com.mvnh.letsplit.domain.repository

interface EventsRepository {
    fun getEvents(userId: String)
    fun createEvent(userId: String)
    fun getEventDetails()
}