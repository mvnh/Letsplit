package com.mvnh.letsplit.domain.model

data class InvitationDetails(
    val imageUrl: String,
    val creatorName: String,
    val eventName: String,
    val until: String,
    val status: String
)
