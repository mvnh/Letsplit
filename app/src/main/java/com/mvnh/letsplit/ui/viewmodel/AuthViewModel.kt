package com.mvnh.letsplit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvnh.letsplit.domain.usecases.AuthUseCase
import com.mvnh.letsplit.ui.viewmodel.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val useCase: AuthUseCase) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> get() = _authState

    fun signUp(fullName: String, phoneNumber: String, password: String) {
        _authState.value = AuthState.Loading

         viewModelScope.launch {
             val result = useCase.register(fullName, phoneNumber, password)

             Log.d("Letsplit", phoneNumber)

             _authState.value = when {
                 result.isSuccess -> {
                     Log.d("Letsplit", result.getOrThrow().toString())
                     AuthState.Success(result.getOrThrow())
                 }

                 else -> {
                     Log.e("Letsplit", result.exceptionOrNull()?.message ?: "An error occurred")
                     AuthState.Error(result.exceptionOrNull()?.message ?: "An error occurred")
                 }
             }
         }
    }

    fun signIn(phoneNumber: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = useCase.login(phoneNumber, password)

            _authState.value = when {
                result.isSuccess -> {
                    AuthState.Success(result.getOrThrow())
                }

                else -> {
                    AuthState.Error(result.exceptionOrNull()?.message ?: "An error occurred")
                }
            }
        }
    }
}