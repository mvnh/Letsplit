package com.mvnh.letsplit.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthTokensDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String = "bearer",
)
