package com.mvnh.letsplit.domain.model

data class AuthTokens(
    val accessToken: String,
    val tokenType: String = "bearer"
)
