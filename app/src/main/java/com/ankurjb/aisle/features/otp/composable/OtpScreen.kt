package com.ankurjb.aisle.features.otp.composable

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
fun OtpScreen(
    phoneNumber: PhoneNumber?, navigateToLogin: () -> Unit, navigateToNotes: (String) -> Unit
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    UserOtpScreen(
        paddingValues = it,
        phoneNumber = phoneNumber,
        onEditClick = navigateToLogin,
        onSubmitClick = navigateToNotes
    )
}

@Composable
private fun UserOtpScreen(
    paddingValues: PaddingValues,
    phoneNumber: PhoneNumber?,
    onEditClick: () -> Unit,
    onSubmitClick: (String) -> Unit
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
    VerticalSpacer(height = 80.dp)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onEditClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${phoneNumber?.countryCode.orEmpty()} ${phoneNumber?.phoneNumber.orEmpty()}",
            style = textStyleRegular
        )
        HorizontalSpacer(width = 8.dp)
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.ic_action_edit),
            contentDescription = stringResource(R.string.edit_option)
        )
    }

    Text(text = "Enter The OTP", style = textStyleHeading)

    VerticalSpacer(height = 12.dp)

    var otp by remember {
        mutableStateOf("")
    }
    TextInputField(
        modifier = Modifier.width(88.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        value = otp
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
