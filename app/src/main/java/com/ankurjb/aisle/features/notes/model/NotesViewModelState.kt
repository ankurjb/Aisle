package com.ankurjb.aisle.features.notes.model

import com.ankurjb.aisle.common.model.UiState

data class NotesViewModelState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val heroProfile: HeroProfile? = null
) {

    fun toUiState() = NotesUiState(
        uiState = when {
            isLoading -> UiState.Loading
            isSuccess -> UiState.Success
            isError -> UiState.Failure
            else -> UiState.Success
        },
        heroProfile = heroProfile
    )
}

data class NotesUiState(
    val uiState: UiState,
    val heroProfile: HeroProfile?
)
