package fr.thomasbernard03.feed.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
    price : Double,
    onClick : () -> Unit = {}
) {
    Box {
        ElevatedButton(
            onClick = onClick,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .height(200.dp)
                .padding(top = 30.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium)

                Text(
                    text = description,
                    style = MaterialTheme.typography.titleSmall)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "rating")
                    Text(text = rating.toString())

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = price.toString())
                }
            }
        }

        AsyncImage(
            model = picture,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .align(Alignment.TopCenter))
    }
}