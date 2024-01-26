package fr.thomasbernard03.feed.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.presentation.components.CartItem
import fr.thomasbernard03.feed.presentation.components.PrimaryButton

@Composable
fun CartScreen(
    state: CartUiState,
    onEvent: (CartEvent) -> Unit
) {

    LaunchedEffect(Unit){
        onEvent(CartEvent.OnGetCart)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.products != null){
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ){
                items(state.products) { product ->
                    CartItem(
                        image = product.image,
                        label = product.label,
                        description = product.description,
                        rating = 0.0,
                        quantity = product.quantity,
                        onAddProduct = { onEvent(CartEvent.OnAddProduct(product))},
                        onRemoveProduct = { onEvent(CartEvent.OnRemoveProduct(product))}
                    )
                }
            }
        }

        PrimaryButton(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = stringResource(id = R.string.validate_cart),
            enabled = !state.products.isNullOrEmpty(),
            onClick = {

            }
        )
    }
}