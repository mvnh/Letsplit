package com.mvnh.letsplit.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserDto(
    @SerialName("phone") val phoneNumber: String,
    val password: String
)
