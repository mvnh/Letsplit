package com.mvnh.letsplit.data.network.service

import com.mvnh.letsplit.data.mapper.toDto
import com.mvnh.letsplit.data.network.dto.DetailResponseDto
import com.mvnh.letsplit.domain.model.AuthTokens
import com.mvnh.letsplit.domain.model.LoginUser
import com.mvnh.letsplit.domain.model.RegisterUser
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess


class AuthService(private val client: HttpClient) {
    suspend fun register(credentials: RegisterUser): Result<AuthTokens> {
        return try {
            val response = client.post("/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(credentials.toDto())
            }

            if (response.status.isSuccess()) {
                Result.success(response.body<AuthTokens>())
            } else {
                Result.failure(Exception(response.body<DetailResponseDto>().detail["ru"]))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(credentials: LoginUser): Result<AuthTokens> {
        return try {
            val response = client.post("/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(credentials.toDto())
            }

            if (response.status.isSuccess()) {
                Result.success(response.body<AuthTokens>())
            } else {
                Result.failure(Exception(response.body<DetailResponseDto>().detail["ru"]))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}