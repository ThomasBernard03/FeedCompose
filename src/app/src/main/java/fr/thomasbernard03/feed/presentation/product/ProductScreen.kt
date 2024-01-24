package fr.thomasbernard03.feed.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
                icon = Icons.Filled.ArrowBack,
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
                    model = state.product.picture,
                    contentDescription = state.product.title,
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
                        text = state.product.title,
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

                    Row {
                        PrimaryButton(
                            text = stringResource(id = R.string.add_to_cart),
                            onClick = { onEvent(ProductEvent.OnAddToCart(state.product)) },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
        }
    }
}