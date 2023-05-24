package com.ankurjb.aisle.features.notes.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankurjb.aisle.R
import com.ankurjb.aisle.common.composable.ConfirmButton
import com.ankurjb.aisle.common.composable.ProgressBar
import com.ankurjb.aisle.common.model.UiState
import com.ankurjb.aisle.features.notes.NotesViewModel
import com.ankurjb.aisle.features.notes.model.NotesUiState
import com.ankurjb.aisle.ui.theme.textStyleMedium
import com.ankurjb.aisle.ui.theme.textStyleName
import com.ankurjb.aisle.ui.theme.textStyleSubHeading
import com.ankurjb.aisle.ui.theme.textStyleSubText
import com.ankurjb.aisle.utils.HorizontalSpacer
import com.ankurjb.aisle.utils.VerticalSpacer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    val uiState by viewModel.notesUiState.collectAsState()
    UserNotesScreen(paddingValues = it, uiState)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserNotesScreen(
    paddingValues: PaddingValues,
    uiState: NotesUiState
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
) {
    ProgressBar(uiState.uiState is UiState.Loading)
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

    uiState.heroProfile?.let {
        VerticalSpacer(height = 8.dp)

        Box(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                model = it.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = it.nameAndAge,
                    style = textStyleName
                )
            }
        }
    }

    VerticalSpacer(height = 8.dp)

    Text(
        text = stringResource(R.string.interested_in_you),
        style = textStyleSubHeading
    )

    VerticalSpacer(height = 4.dp)

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Bottom),
            text = stringResource(R.string.premium_feature),
            style = textStyleSubText
        )

        HorizontalSpacer(width = 24.dp)
        ConfirmButton(title = stringResource(R.string.upgrade)) {

        }
    }


}
