package com.ankurjb.aisle.features.otp.model

import com.ankurjb.aisle.common.model.UiState

data class OtpViewModelState(
    val phoneNumber: String,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
) {
    fun toUiState() = OtpUiState(
        uiState = when {
            isLoading -> UiState.Loading
            isSuccess -> UiState.Success
            else -> UiState.Success
        },
        phoneNumber = phoneNumber
    )
}

data class OtpUiState(
    val uiState: UiState,
    val phoneNumber: String
)
