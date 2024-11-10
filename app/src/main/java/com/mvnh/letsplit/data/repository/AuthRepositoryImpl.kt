package com.mvnh.letsplit.data.repository

import com.mvnh.letsplit.data.network.service.AuthService
import com.mvnh.letsplit.domain.model.AuthTokens
import com.mvnh.letsplit.domain.model.LoginUser
import com.mvnh.letsplit.domain.model.RegisterUser
import com.mvnh.letsplit.domain.repository.AuthRepository

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun register(credentials: RegisterUser): Result<AuthTokens> {
        return service.register(credentials)
    }

    override suspend fun login(credentials: LoginUser): Result<AuthTokens> {
        return service.login(credentials)
    }
}