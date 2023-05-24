package com.ankurjb.aisle.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.aisle.features.notes.model.NotesUiState
import com.ankurjb.aisle.features.notes.model.NotesViewModelState
import com.ankurjb.aisle.mapper.NotesMapper
import com.ankurjb.aisle.model.Profile
import com.ankurjb.aisle.usecase.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCase: NotesUseCase,
    private val mapper: NotesMapper
) : ViewModel() {

    private val _isError = MutableSharedFlow<Boolean>()
    val isError: SharedFlow<Boolean> = _isError

    private val _viewModelState = MutableStateFlow(NotesViewModelState())
    val notesUiState: StateFlow<NotesUiState> = _viewModelState.map {
        it.toUiState()
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _viewModelState.value.toUiState()
    )

    init {
        getInitialData()
    }

    private fun getInitialData() = viewModelScope.launch {
        _viewModelState.update { it.copy(isLoading = true) }
        val result: Profile? = useCase()
        result?.let { profile ->
            _viewModelState.update {
                it.copy(
                    heroProfile = mapper.toNotesHeroProfile(profile),
                    isLoading = false
                )
            }
        } ?: _viewModelState.update { it.copy(isLoading = false) }
    }
}
