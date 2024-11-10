package com.mvnh.letsplit.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Events : Screen("events")
    data object EventInvitations : Screen("eve  nt_invitations")
    data object Profile : Screen("profile")
    data object Details: Screen("details")
    data object CreateEvent: Screen("create_event")
}