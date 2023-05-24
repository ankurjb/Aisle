package com.ankurjb.aisle.features.login.model

import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.common.model.UiState

data class LoginViewModelState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val phoneNumber: PhoneNumber = PhoneNumber(
        countryCode = "+91",
        number = "9876543212"
    )
) {
    fun toUiState() = LoginUiState(
        uiState = when {
            isLoading -> UiState.Loading
            isSuccess -> UiState.Success
            else -> UiState.Success
        },
        phoneNumber = phoneNumber
    )
}

data class LoginUiState(
    val uiState: UiState,
    val phoneNumber: PhoneNumber
)
