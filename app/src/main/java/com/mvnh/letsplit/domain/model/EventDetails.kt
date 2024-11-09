package com.mvnh.letsplit.domain.model

import com.mvnh.letsplit.ui.viewmodel.state.BalanceStatus
import java.time.LocalDate

data class EventDetails(
    val title: String,
    val deadline: LocalDate,
    val targetAmount: Int,
    val collectedAmount: Int,
    val balanceStatus: BalanceStatus
)