package fr.thomasbernard03.feed.presentation.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.presentation.components.CartItem

@Composable
fun CartScreen(
    state: CartUiState,
    onEvent: (CartEvent) -> Unit
) {

    LaunchedEffect(Unit){
        onEvent(CartEvent.OnGetCart)
    }

    if (state.products != null){
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ){
            items(state.products) { product ->
                CartItem(
                    image = product.image,
                    label = product.label,
                    description = product.description,
                    rating = 0.0,
                    quantity = product.quantity,
                    onAddProduct = {

                    },
                    onRemoveProduct = {

                    }
                )
            }
        }
    }
}