package com.mvnh.letsplit.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mvnh.letsplit.domain.usecases.AuthUseCase

class AuthViewModel(private val useCase: AuthUseCase) : ViewModel()