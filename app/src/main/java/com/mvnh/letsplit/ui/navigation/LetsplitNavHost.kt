package com.mvnh.letsplit.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mvnh.letsplit.ui.screen.EventInvitationsScreen
import com.mvnh.letsplit.ui.screen.EventsScreen
import com.mvnh.letsplit.ui.screen.ProfileScreen
import com.mvnh.letsplit.ui.screen.WelcomeScreen

@Composable
fun LetsplitNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
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