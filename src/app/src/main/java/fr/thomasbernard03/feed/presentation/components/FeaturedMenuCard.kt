package fr.thomasbernard03.feed.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun FeaturedMenuCard(
    modifier : Modifier = Modifier,
    label : String,
    price : Double,
    picture : String,
    onClick : () -> Unit = { }
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopStart)
                    .padding(4.dp)
                    .zIndex(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = "$price €",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(2.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.order),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge)
                }
            }

            AsyncImage(
                model = picture,
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun FeaturedMenuCardPreview() {
    FeedTheme {
        Surface {
            Column {
                FeaturedMenuCard(
                    label = "Menu Burger",
                    price = 12.5,
                    picture = "https://png.pngtree.com/png-clipart/20230928/original/pngtree-burger-png-images-png-image_13164941.png"
                )

                Spacer(modifier = Modifier.height(16.dp))

                FeaturedMenuCard(
                    label = "Menu hot dog",
                    price = 9.5,
                    picture = "https://i.pinimg.com/originals/9e/ca/5b/9eca5b7cdf5822aaf6cdb86bb7b53437.png"
                )
            }
        }
    }
}