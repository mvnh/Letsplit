package com.mvnh.letsplit.domain.usecases

import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.mvnh.letsplit.domain.model.AuthTokens
import com.mvnh.letsplit.domain.model.LoginUser
import com.mvnh.letsplit.domain.model.RegisterUser
import com.mvnh.letsplit.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) {
    private fun checkCredentials(phoneNumber: String, password: String): Boolean {
        return phoneNumber.startsWith("+7") && password.length >= 8
    }

    fun saveUserId(accessToken: String) {
        val jwt: JWT = JWT(accessToken)

        val userId = jwt.subject

        sharedPreferences.edit()
            .putString("user_id", userId)
            .apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
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