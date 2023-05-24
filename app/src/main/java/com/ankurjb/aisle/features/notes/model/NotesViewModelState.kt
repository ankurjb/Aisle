package com.ankurjb.aisle.features.notes.model

import com.ankurjb.aisle.common.model.UiState

data class NotesViewModelState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val heroProfile: HeroProfile? = null,
    val interestedInYou: List<InterestedProfiles> = emptyList(),
    val upgradeAccount: Boolean = false
) {

    fun toUiState() = NotesUiState(
        uiState = when {
            isLoading -> UiState.Loading
            isSuccess -> UiState.Success
            isError -> UiState.Failure
            else -> UiState.Success
        },
        heroProfile = heroProfile,
        interestedInYou = interestedInYou,
        upgradeAccount = upgradeAccount
    )
}

data class NotesUiState(
    val uiState: UiState,
    val heroProfile: HeroProfile?,
    val interestedInYou: List<InterestedProfiles>,
    val upgradeAccount: Boolean
)
