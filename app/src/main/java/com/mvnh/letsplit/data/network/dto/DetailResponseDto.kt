package com.mvnh.letsplit.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class DetailResponseDto(
    val detail: Map<String, String>
)
