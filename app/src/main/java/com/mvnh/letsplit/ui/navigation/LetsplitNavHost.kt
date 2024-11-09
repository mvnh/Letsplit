package com.mvnh.letsplit.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mvnh.letsplit.ui.screen.main.EventInvitationsScreen
import com.mvnh.letsplit.ui.screen.main.EventsScreen
import com.mvnh.letsplit.ui.screen.main.ProfileScreen
import com.mvnh.letsplit.ui.screen.auth.WelcomeScreen

@Composable
fun LetsplitNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Events.route,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen()
        }
        composable(Screen.Events.route) {
            EventsScreen()
        }
        composable(Screen.EventInvitations.route) {
            EventInvitationsScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}