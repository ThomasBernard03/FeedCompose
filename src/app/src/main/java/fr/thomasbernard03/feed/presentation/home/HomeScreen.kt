package fr.thomasbernard03.feed.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.presentation.components.FeaturedMenuCard
import fr.thomasbernard03.feed.presentation.components.ProductCard
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun HomeScreen(
    state : HomeUiState,
    onEvent: (HomeEvent) -> Unit
) {
    LaunchedEffect(Unit){
        onEvent(HomeEvent.OnGetFeaturedMenu)
        onEvent(HomeEvent.OnGetProducts)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
        ) {
            items(state.featuredMenus ?: emptyList()) {
                FeaturedMenuCard(
                    modifier = Modifier
                        .height(100.dp)
                        .width(200.dp)
                        .padding(horizontal = 4.dp),
                    label = it.title,
                    price = it.price,
                    picture = it.picture)
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.products ?: emptyList()) {
                ProductCard(
                    picture = it.picture,
                    label = it.title,
                    description = it.description,
                    rating = it.rating,
                    price = it.price,
                    onClick = { onEvent(HomeEvent.OnProductClicked(it)) }
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    FeedTheme {
        Surface {
            HomeScreen(
                state = HomeUiState(),
                onEvent = {}
            )
        }
    }
}