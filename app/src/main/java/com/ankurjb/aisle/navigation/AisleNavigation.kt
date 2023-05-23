package com.ankurjb.aisle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankurjb.aisle.features.login.composable.LoginScreen
import com.ankurjb.aisle.features.notes.composable.NotesScreen
import com.ankurjb.aisle.features.otp.composable.OtpScreen

@Composable
fun AisleNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = Navigator.getStartNavigation(),
        navController = navController
    ) {
        composable(route = Navigator.LoginScreen.route) {
            LoginScreen { phoneNumber ->
                navController.navigateToOtpScreen(phoneNumber)
            }
        }
        composable(route = Navigator.OtpScreen.route) {
            OtpScreen(
                navigateToLogin = {
                    navController.navigateUp()
                }
            ) {
                navController.popBackStack(Navigator.LoginScreen.route, true)
                navController.navigateToNotesScreen()
            }
        }
        composable(route = Navigator.NotesScreen.route) {
            NotesScreen()
        }
    }
}
