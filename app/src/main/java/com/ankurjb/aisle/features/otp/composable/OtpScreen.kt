package com.ankurjb.aisle.features.otp.composable

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankurjb.aisle.R
import com.ankurjb.aisle.common.composable.ConfirmButton
import com.ankurjb.aisle.common.composable.ProgressBar
import com.ankurjb.aisle.common.composable.TextInputField
import com.ankurjb.aisle.common.model.UiState
import com.ankurjb.aisle.features.otp.OtpViewModel
import com.ankurjb.aisle.features.otp.model.OtpUiState
import com.ankurjb.aisle.ui.theme.textStyleHeading
import com.ankurjb.aisle.ui.theme.textStyleRegular
import com.ankurjb.aisle.utils.HorizontalSpacer
import com.ankurjb.aisle.utils.VerticalSpacer
import com.ankurjb.aisle.utils.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OtpScreen(
    viewModel: OtpViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navigateToLogin: () -> Unit,
    navigateToNotes: () -> Unit
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    val uiState by viewModel.otpUiState.collectAsState()
    UserOtpScreen(
        paddingValues = it,
        uiState = uiState,
        onEditClick = navigateToLogin,
        onSubmitClick = viewModel::submitOtp
    )

    LaunchedEffect(Unit) {
        viewModel.isError.collectLatest {
            if (it) showToast(context, context.getString(R.string.something_went_wrong))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isOtpVerified.collectLatest {
            if (it) navigateToNotes()
        }
    }
}

@Composable
private fun UserOtpScreen(
    paddingValues: PaddingValues,
    uiState: OtpUiState,
    onEditClick: () -> Unit,
    onSubmitClick: (String) -> Unit
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
    ProgressBar(uiState.uiState is UiState.Loading)
    VerticalSpacer(height = 80.dp)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onEditClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = uiState.phoneNumber, style = textStyleRegular
        )
        HorizontalSpacer(width = 8.dp)
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.ic_action_edit),
            contentDescription = stringResource(R.string.edit_option)
        )
    }

    Text(text = stringResource(R.string.enter_the_otp), style = textStyleHeading)

    VerticalSpacer(height = 12.dp)

    var otp by remember {
        mutableStateOf("")
    }
    TextInputField(
        modifier = Modifier.width(88.dp), keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ), value = otp
    ) {
        if (it.length <= 4) otp = it
    }

    VerticalSpacer(height = 18.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConfirmButton(
            isEnabled = otp.isNotEmpty() && otp.length == 4
        ) {
            onSubmitClick(otp)
        }
    }
}
