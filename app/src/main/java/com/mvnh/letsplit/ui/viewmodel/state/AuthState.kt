package com.mvnh.letsplit.ui.viewmodel.state

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success(val data: Any?) : AuthState()
    data class Error(val message: String) : AuthState()
}