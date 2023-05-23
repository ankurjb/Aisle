package com.ankurjb.aisle.common.model

sealed class UiState {
    object Success : UiState()
    object Loading : UiState()
    object Failure : UiState()
}
