package fr.thomasbernard03.feed.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun RoundedIcon(
    modifier : Modifier = Modifier,
    icon: ImageVector,
    onClick : () -> Unit = {}
) {
    ElevatedButton(
        modifier = modifier
            .size(48.dp),
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview
fun RoundedIconPreview() {
    FeedTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                RoundedIcon(
                    icon = Icons.Filled.ArrowBack
                )
            }
        }
    }

}