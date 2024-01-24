package fr.thomasbernard03.feed.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.commons.utils.defaultElevation
import fr.thomasbernard03.feed.presentation.components.PrimaryButton
import fr.thomasbernard03.feed.presentation.components.RoundedIcon

@Composable
fun ProductScreen(
    id : Int,
    state: ProductUiState,
    onEvent: (ProductEvent) -> Unit
) {

    LaunchedEffect(Unit){
        onEvent(ProductEvent.OnGetProduct(id))
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            RoundedIcon(
                icon = R.drawable.arrow,
                onClick = { onEvent(ProductEvent.OnGoBack) }
            )

            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "rate")
        }

        // Product image


        if (state.product != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AsyncImage(
                    model = state.product.image,
                    contentDescription = state.product.label,
                    contentScale = ContentScale.Fit
                )

            }
        }


        // Bottom sheet

        ElevatedCard(
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = defaultElevation
            )
        ) {
            if (state.product != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.product.label,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = state.product.description,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = String.format("", state.product.price),
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        RoundedIcon(
                            icon = R.drawable.remove,
                            onClick = { onEvent(ProductEvent.OnDecrementQuantity) },
                        )

                        Text(text = state.quantity.toString(),
                            style = MaterialTheme.typography.titleLarge)

                        RoundedIcon(
                            icon = R.drawable.add,
                            onClick = { onEvent(ProductEvent.OnIncrementQuantity) },
                        )


                        if (state.product.price != null){
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = String.format("%.2f€", state.product.price * state.quantity),
                                style = MaterialTheme.typography.titleLarge)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    PrimaryButton(
                        enabled = state.quantity > 0,
                        text = stringResource(id = R.string.add_to_cart),
                        onClick = { onEvent(ProductEvent.OnAddToCart(state.product, state.quantity)) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}