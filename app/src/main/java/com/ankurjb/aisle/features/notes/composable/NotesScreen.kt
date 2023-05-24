package com.ankurjb.aisle.features.notes.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankurjb.aisle.R
import com.ankurjb.aisle.common.composable.ConfirmButton
import com.ankurjb.aisle.common.composable.ProgressBar
import com.ankurjb.aisle.common.model.UiState
import com.ankurjb.aisle.features.notes.NotesViewModel
import com.ankurjb.aisle.features.notes.model.InterestedProfiles
import com.ankurjb.aisle.features.notes.model.NotesUiState
import com.ankurjb.aisle.ui.theme.textStyleMedium
import com.ankurjb.aisle.ui.theme.textStyleName
import com.ankurjb.aisle.ui.theme.textStyleSubHeading
import com.ankurjb.aisle.ui.theme.textStyleSubText
import com.ankurjb.aisle.utils.HorizontalSpacer
import com.ankurjb.aisle.utils.VerticalSpacer
import com.ankurjb.aisle.utils.showToast
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(), context: Context = LocalContext.current
) = Scaffold(modifier = Modifier.fillMaxSize()) {
    val uiState by viewModel.notesUiState.collectAsState()
    UserNotesScreen(paddingValues = it, uiState)

    LaunchedEffect(Unit) {
        viewModel.isError.collectLatest {
            if (it) showToast(context, context.getString(R.string.something_went_wrong))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserNotesScreen(
    paddingValues: PaddingValues, uiState: NotesUiState
) = Column(
    modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 30.dp)
        .padding(bottom = 30.dp)
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
                    text = it.nameAndAge, style = textStyleName
                )
            }
        }
    }

    if (uiState.interestedInYou.isNotEmpty()) {
        VerticalSpacer(height = 8.dp)

        Text(
            text = stringResource(R.string.interested_in_you), style = textStyleSubHeading
        )
    }

    if (uiState.upgradeAccount) {

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

    if (uiState.interestedInYou.isNotEmpty()) VerticalSpacer(height = 8.dp)

    for (i in 0 until uiState.interestedInYou.size step 2) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.interestedInYou.getOrNull(i)?.let {
                ShimmeredGlideImage(modifier = Modifier.weight(1f), interestedProfiles = it)
            }

            uiState.interestedInYou.getOrNull(i + 1)?.let {
                ShimmeredGlideImage(modifier = Modifier.weight(1f), interestedProfiles = it)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShimmeredGlideImage(
    modifier: Modifier, interestedProfiles: InterestedProfiles
) = Box(
    modifier = modifier
        .clip(RoundedCornerShape(8.dp))
) {
    GlideImage(
        modifier = modifier
            .blur(if (interestedProfiles.isProfileVisible) 0.dp else 40.dp)
            .aspectRatio(0.5f)
            .clip(RoundedCornerShape(8.dp)),
        model = interestedProfiles.imageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop
    )

    Text(
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.BottomStart),
        text = interestedProfiles.name,
        style = textStyleName
    )
}
