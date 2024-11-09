package com.mvnh.letsplit.ui.viewmodel.state

sealed class BalanceStatus {
    data object Positive : BalanceStatus()
    data object Negative : BalanceStatus()
}