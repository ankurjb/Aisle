package com.ankurjb.aisle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ankurjb.aisle.common.composable.ProgressBar
import com.ankurjb.aisle.navigation.AisleNavigation
import com.ankurjb.aisle.ui.theme.AisleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AisleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val isAuthorised by viewModel.isAuthorised.collectAsState(null)
                    isAuthorised?.let {
                        AisleNavigation(isAuthorised = it)
                    } ?: ProgressBar()
                }
            }
        }
    }
}
