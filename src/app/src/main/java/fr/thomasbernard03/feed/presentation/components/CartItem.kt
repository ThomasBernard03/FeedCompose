package fr.thomasbernard03.feed.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.commons.utils.defaultElevation

@Composable
fun CartItem(
    modifier : Modifier = Modifier,
    image : String,
    label : String,
    description : String,
    rating : Double,
    quantity: Int,
    onAddProduct: () -> Unit,
    onRemoveProduct: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .height(160.dp)
            .padding(top = 30.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = defaultElevation,
        )
    ) {
        Row {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(100.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium)

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall)

                Row(
                    modifier = Modifier.padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "rating")
                    Text(text = rating.toString())
                }
            }

            Column(
                modifier = Modifier
                    .width(80.dp)
                    .weight(1f)
            ) {
                Text(text = quantity.toString())
            }
        }
    }
}