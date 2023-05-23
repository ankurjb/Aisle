package com.ankurjb.aisle.features.login.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankurjb.aisle.R
import com.ankurjb.aisle.common.composable.ConfirmButton
import com.ankurjb.aisle.common.composable.ProgressBar
import com.ankurjb.aisle.common.composable.TextInputField
import com.ankurjb.aisle.common.model.PhoneNumber
import com.ankurjb.aisle.common.model.UiState
import com.ankurjb.aisle.features.login.LoginViewModel
import com.ankurjb.aisle.features.login.model.LoginUiState
import com.ankurjb.aisle.ui.theme.textStyleHeading
import com.ankurjb.aisle.ui.theme.textStyleRegular
import com.ankurjb.aisle.utils.HorizontalSpacer
import com.ankurjb.aisle.utils.VerticalSpacer
import com.ankurjb.aisle.utils.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navigateToOtp: (PhoneNumber) -> Unit
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    val uiState by viewModel.loginUiState.collectAsState()

    UserLoginScreen(
        paddingValues = it,
        uiState = uiState,
        phoneNumber = uiState.phoneNumber,
        onSubmitClick = viewModel::submitPhoneNumber
    )

    LaunchedEffect(Unit) {
        viewModel.isError.collectLatest { isError ->
            if (isError) showToast(context, context.getString(R.string.something_went_wrong))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isLoginAPISuccess.collectLatest { isSuccess ->
            if (isSuccess) navigateToOtp(uiState.phoneNumber)
        }
    }
}

@Composable
private fun UserLoginScreen(
    paddingValues: PaddingValues,
    uiState: LoginUiState,
    phoneNumber: PhoneNumber,
    onSubmitClick: (PhoneNumber) -> Unit
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
    ProgressBar(uiState.uiState is UiState.Loading)
    VerticalSpacer(height = 80.dp)
    Text(
        text = stringResource(R.string.get_otp), style = textStyleRegular
    )

    VerticalSpacer(height = 8.dp)
    Text(
        text = stringResource(R.string.enter_your_phone_number), style = textStyleHeading
    )

    VerticalSpacer(height = 8.dp)
    var countryCode by remember {
        mutableStateOf(phoneNumber.countryCode)
    }
    var number by remember {
        mutableStateOf(phoneNumber.number)
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextInputField(
            modifier = Modifier.width(84.dp),
            value = countryCode
        ) {
            countryCode = it
        }

        HorizontalSpacer(width = 8.dp)
        TextInputField(
            value = number
        ) {
            number = it
        }
    }

    VerticalSpacer(height = 18.dp)

    ConfirmButton(
        isEnabled = countryCode.isNotEmpty() && number.isNotEmpty()
    ) {
        onSubmitClick(
            PhoneNumber(
                countryCode = countryCode.trim(),
                number = number.trim()
            )
        )
    }
}
