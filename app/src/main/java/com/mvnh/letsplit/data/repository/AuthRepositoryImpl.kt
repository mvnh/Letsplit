package com.mvnh.letsplit.data.repository

import com.mvnh.letsplit.data.network.service.AuthService
import com.mvnh.letsplit.domain.repository.AuthRepository

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository