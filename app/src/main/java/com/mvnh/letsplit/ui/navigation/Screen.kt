package com.mvnh.letsplit.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Events : Screen("events")
    data object EventInvitations : Screen("event_invitations")
    data object Profile : Screen("profile")
    data object Bills: Screen("bills")
    data object CreateEvent: Screen("create_event")
}