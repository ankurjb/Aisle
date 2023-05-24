package com.ankurjb.aisle.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.features.otp.OtpViewModel
import com.ankurjb.aisle.utils.navigateTo

sealed class Navigator(val route: String) {
    object LoginScreen : Navigator("login_screen")
    object OtpScreen : Navigator("otp_screen")
    object NotesScreen : Navigator("notes_screen")

    companion object {
        fun getStartNavigation(isAuthorised: Boolean) =
            if (isAuthorised) NotesScreen.route else LoginScreen.route
    }
}

fun NavHostController.navigateToOtpScreen(
    phoneNumber: PhoneNumber
) = navigateTo(
    route = Navigator.OtpScreen.route,
    args = bundleOf(OtpViewModel.PHONE_NUMBER to phoneNumber)
)

fun NavHostController.navigateToNotesScreen() = navigateTo(
    route = Navigator.NotesScreen.route
)
