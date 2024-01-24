package fr.thomasbernard03.feed.presentation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text : String,
    enabled: Boolean = true,
    loading : Boolean = false,
    @DrawableRes leadingIcon : Int? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        modifier = modifier.height(54.dp),
        enabled = enabled,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        if (loading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier
                .height(25.dp)
                .width(25.dp), strokeWidth = 3.dp)
        }
        else {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "Save",
                    tint = Color.White)

                Spacer(modifier = Modifier.width(8.dp))
            }


            Text(
                text.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrimaryButtonPreview() {
    FeedTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                PrimaryButton(text = "BUTTON", onClick = { }, modifier = Modifier.fillMaxWidth())
                PrimaryButton(text = "BUTTON", onClick = { }, enabled = false, modifier = Modifier.fillMaxWidth())
                PrimaryButton(text = "BUTTON", onClick = { }, enabled = true, loading = true, modifier = Modifier.fillMaxWidth())
                PrimaryButton(text = "BUTTON", onClick = { }, enabled = false, loading = true, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}