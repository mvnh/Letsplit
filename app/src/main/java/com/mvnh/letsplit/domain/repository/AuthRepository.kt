package com.mvnh.letsplit.domain.repository

import com.mvnh.letsplit.domain.model.AuthTokens
import com.mvnh.letsplit.domain.model.LoginUser
import com.mvnh.letsplit.domain.model.RegisterUser
import com.mvnh.letsplit.ui.viewmodel.state.AuthState

interface AuthRepository {
    suspend fun register(credentials: RegisterUser): Result<AuthTokens>
    suspend fun login(credentials: LoginUser): Result<AuthTokens>
}