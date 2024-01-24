package fr.thomasbernard03.feed.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
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

@Composable
fun ProductCard(
    modifier : Modifier = Modifier,
    picture : String,
    label : String,
    description : String,
    rating : Double,
    price : Double? = null,
    onClick : () -> Unit = {}
) {
    Box {
        ElevatedButton(
            onClick = onClick,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .height(160.dp)
                .padding(top = 30.dp),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleMedium)

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall)
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.star), contentDescription = "rating")
                        Text(text = rating.toString())
                    }


                    Spacer(modifier = Modifier.weight(1f))

                    if (price != null) {
                        Text(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 8.dp))
                                .padding(4.dp),
                            text = String.format("%.2f€", price),
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }

        AsyncImage(
            model = picture,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.TopCenter)
        )
    }
}