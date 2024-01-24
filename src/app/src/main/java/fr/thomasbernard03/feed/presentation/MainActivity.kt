package fr.thomasbernard03.feed.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.thomasbernard03.feed.presentation.home.HomeScreen
import fr.thomasbernard03.feed.presentation.home.HomeViewModel
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val homeViewModel : HomeViewModel = viewModel()
                    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                    HomeScreen(
                        state = uiState,
                        onEvent = homeViewModel::onEvent
                    )
                }
            }
        }
    }
}