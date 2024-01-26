package fr.thomasbernard03.feed.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(100.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .width(42.dp)
                    .fillMaxHeight()
            ) {
                Button(
                    modifier = Modifier.weight(1.5f),
                    shape = RectangleShape,
                    onClick = onAddProduct,
                    contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "+")
                }

                Text(
                    modifier = Modifier
                        .weight(2f)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    text = quantity.toString()
                )


                Button(
                    modifier = Modifier.weight(1.5f),
                    shape = RectangleShape,
                    onClick = onRemoveProduct,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "-")
                }
            }
        }
    }
}