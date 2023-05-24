package com.ankurjb.aisle.features.otp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.features.otp.model.OtpUiState
import com.ankurjb.aisle.features.otp.model.OtpViewModelState
import com.ankurjb.aisle.usecase.OtpUseCase
import com.ankurjb.aisle.utils.LocalStorage
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
class OtpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: OtpUseCase,
    private val dataStore: DataStore<Preferences>
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
        val result = useCase(phoneNumber, otp)
        if (result != null) {
            dataStore.edit { it[LocalStorage.AuthToken] = result }
            _isOtpVerified.emit(true)
        } else {
            _isError.emit(true)
        }
        _viewModelState.update { it.copy(isLoading = false) }
    }


    companion object {
        const val PHONE_NUMBER = "PHONE_NUMBER"
    }
}
