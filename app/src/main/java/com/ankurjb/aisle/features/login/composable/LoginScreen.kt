package com.ankurjb.aisle.features.login.composable

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ankurjb.aisle.R
import com.ankurjb.aisle.common.composable.ConfirmButton
import com.ankurjb.aisle.common.composable.TextInputField
import com.ankurjb.aisle.model.PhoneNumber
import com.ankurjb.aisle.ui.theme.textStyleHeading
import com.ankurjb.aisle.ui.theme.textStyleRegular
import com.ankurjb.aisle.utils.HorizontalSpacer
import com.ankurjb.aisle.utils.VerticalSpacer

@Composable
fun LoginScreen(
    navigateToOtp: (PhoneNumber) -> Unit
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    UserLoginScreen(paddingValues = it, navigateToOtp)
}

@Composable
private fun UserLoginScreen(
    paddingValues: PaddingValues, onSubmitClick: (PhoneNumber) -> Unit
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
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
        mutableStateOf("+91")
    }
    var phoneNumber by remember {
        mutableStateOf("")
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
            value = phoneNumber
        ) {
            phoneNumber = it
        }
    }

    VerticalSpacer(height = 18.dp)

    ConfirmButton(
        isEnabled = countryCode.isNotEmpty() && phoneNumber.isNotEmpty()
    ) {
        onSubmitClick(
            PhoneNumber(
                countryCode = countryCode.trim(),
                phoneNumber = phoneNumber.trim()
            )
        )
    }
}
