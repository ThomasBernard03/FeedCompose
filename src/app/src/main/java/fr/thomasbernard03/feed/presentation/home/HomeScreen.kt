package fr.thomasbernard03.feed.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.presentation.components.ProductCard
import fr.thomasbernard03.feed.presentation.components.TextField
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun HomeScreen(
    state : HomeUiState,
    onEvent: (HomeEvent) -> Unit
) {
    LaunchedEffect(Unit){
        onEvent(HomeEvent.OnGetProducts)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TextField(
            modifier = Modifier.padding(8.dp),
            text = state.query,
            placeholder = stringResource(
            id = R.string.search_product),
            leadingIcon = R.drawable.search,
            onTextChange = { onEvent(HomeEvent.OnQueryChanged(it)) })

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.products?.filter { it.label.lowercase().contains(state.query.lowercase()) || it.description.lowercase().contains(state.query.lowercase()) } ?: emptyList()) {
                ProductCard(
                    picture = it.image,
                    label = it.label,
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