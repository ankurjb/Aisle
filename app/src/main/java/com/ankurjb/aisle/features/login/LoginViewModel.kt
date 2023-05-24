package com.ankurjb.aisle.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.features.login.model.LoginUiState
import com.ankurjb.aisle.features.login.model.LoginViewModelState
import com.ankurjb.aisle.usecase.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
) : ViewModel() {

    private val _isError = MutableSharedFlow<Boolean>()
    val isError: SharedFlow<Boolean> = _isError

    private val _isLoginAPISuccess = MutableSharedFlow<Boolean>()
    val isLoginAPISuccess: SharedFlow<Boolean> = _isLoginAPISuccess

    private val _viewModelState = MutableStateFlow(LoginViewModelState())
    val loginUiState: StateFlow<LoginUiState> = _viewModelState.map {
        it.toUiState()
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _viewModelState.value.toUiState()
    )

    fun submitPhoneNumber(
        phoneNumber: PhoneNumber
    ) = viewModelScope.launch {
        _viewModelState.update { it.copy(isLoading = true, phoneNumber = phoneNumber) }
        val result = useCase(phoneNumber)
        if (result) {
            _isLoginAPISuccess.emit(true)
        } else {
            _isError.emit(true)
        }
        _viewModelState.update { it.copy(isLoading = false) }
    }
}
