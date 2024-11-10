package com.mvnh.letsplit.domain.usecases

import com.mvnh.letsplit.domain.model.AuthTokens
import com.mvnh.letsplit.domain.model.LoginUser
import com.mvnh.letsplit.domain.model.RegisterUser
import com.mvnh.letsplit.domain.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    private fun checkCredentials(phoneNumber: String, password: String): Boolean {
        return phoneNumber.startsWith("+7") && password.length >= 8
    }

    suspend fun register(fullName: String, phoneNumber: String, password: String): Result<AuthTokens> {
        if (!checkCredentials(phoneNumber, password)) {
            return Result.failure(IllegalArgumentException("Invalid phone number or password"))
        }

        return repository.register(
            RegisterUser(fullName, phoneNumber, password)
        )
    }

    suspend fun login(phoneNumber: String, password: String): Result<AuthTokens> {
        if (!checkCredentials(phoneNumber, password)) {
            return Result.failure(IllegalArgumentException("Invalid phone number or password"))
        }

        return repository.login(
            LoginUser(phoneNumber, password)
        )
    }
}