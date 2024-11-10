package com.mvnh.letsplit.data.mapper

import com.mvnh.letsplit.data.network.dto.AuthTokensDto
import com.mvnh.letsplit.domain.model.AuthTokens

fun AuthTokensDto.toModel(): AuthTokens {
    return AuthTokens(accessToken, tokenType)
}

fun AuthTokens.toDto(): AuthTokensDto {
    return AuthTokensDto(accessToken, tokenType)
}