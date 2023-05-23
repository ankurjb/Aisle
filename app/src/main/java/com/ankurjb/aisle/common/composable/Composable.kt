package com.ankurjb.aisle.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankurjb.aisle.R
import com.ankurjb.aisle.ui.theme.TextBlack
import com.ankurjb.aisle.ui.theme.Yellow
import com.ankurjb.aisle.ui.theme.textStyleInputText

@Composable
fun ConfirmButton(
    isEnabled: Boolean = true,
    onClick: () -> Unit
) = TextButton(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = Yellow,
        disabledBackgroundColor = Color.LightGray
    ),
    enabled = isEnabled,
    shape = RoundedCornerShape(20.dp)
) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        text = stringResource(R.string.continue_),
        style = TextStyle(
            color = TextBlack,
            lineHeight = 16.94.sp,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChange: (String) -> Unit
) = OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    maxLines = 1,
    textStyle = textStyleInputText,
    keyboardOptions = keyboardOptions
)

@Composable
fun ProgressBar(isVisible: Boolean = true) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
