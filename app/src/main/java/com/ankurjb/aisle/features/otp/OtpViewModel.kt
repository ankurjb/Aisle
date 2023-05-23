package com.ankurjb.aisle.features.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.features.otp.model.OtpUiState
import com.ankurjb.aisle.features.otp.model.OtpViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
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
class OtpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val phoneNumber by lazy {
        savedStateHandle.get<PhoneNumber>(PHONE_NUMBER)
    }

    private val _isError = MutableSharedFlow<Boolean>()
    val isError: SharedFlow<Boolean> = _isError

    private val _isOtpVerified = MutableSharedFlow<Boolean>()
    val isOtpVerified: SharedFlow<Boolean> = _isOtpVerified

    private val _viewModelState = MutableStateFlow(
        OtpViewModelState(phoneNumber = "${phoneNumber?.countryCode.orEmpty()} ${phoneNumber?.number.orEmpty()}")
    )
    val otpUiState: StateFlow<OtpUiState> = _viewModelState.map {
        it.toUiState()
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _viewModelState.value.toUiState()
    )

    fun submitOtp(otp: String) = viewModelScope.launch {
        _viewModelState.update { it.copy(isLoading = true) }

        delay(2000L)
        _isOtpVerified.emit(true)
        _viewModelState.update { it.copy(isLoading = false) }
    }


    companion object {
        const val PHONE_NUMBER = "PHONE_NUMBER"
    }
}
