package com.ankurjb.aisle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankurjb.aisle.features.login.composable.LoginScreen
import com.ankurjb.aisle.features.notes.composable.NotesScreen
import com.ankurjb.aisle.features.otp.composable.OtpScreen
import com.ankurjb.aisle.model.PhoneNumber

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
            val phoneNumber = it.arguments?.getParcelable<PhoneNumber>("PHONE_NUMBER")
            OtpScreen(
                phoneNumber = phoneNumber,
                navigateToLogin = {
                    navController.navigateUp()
                }
            ) {
                navController.navigateToNotesScreen()
            }
        }
        composable(route = Navigator.NotesScreen.route) {
            NotesScreen()
        }
    }
}
