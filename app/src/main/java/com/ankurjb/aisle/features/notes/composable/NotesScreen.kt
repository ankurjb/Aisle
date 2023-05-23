package com.ankurjb.aisle.features.notes.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ankurjb.aisle.R
import com.ankurjb.aisle.ui.theme.textStyleMedium
import com.ankurjb.aisle.ui.theme.textStyleSubHeading
import com.ankurjb.aisle.utils.VerticalSpacer

@Composable
fun NotesScreen(
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    UserNotesScreen(paddingValues = it)
}

@Composable
fun UserNotesScreen(
    paddingValues: PaddingValues
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
    VerticalSpacer(height = 36.dp)
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.notes),
        style = textStyleSubHeading,
        textAlign = TextAlign.Center
    )

    VerticalSpacer(height = 8.dp)
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Personal messages to you",
        style = textStyleMedium,
        textAlign = TextAlign.Center
    )
}
