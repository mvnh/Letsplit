package com.mvnh.letsplit.domain.model

data class EventRequest(
    val name: String,
    val description: String,
    val createdAt: String,
    val until: String,
    val ownerUuid: String
)